package com.example.tyremonitor

import android.content.Intent
import android.graphics.Typeface
import android.os.Bundle
import android.view.Gravity
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import org.tensorflow.lite.Interpreter
import java.io.FileInputStream
import java.nio.MappedByteBuffer
import java.nio.channels.FileChannel

class AnalyticsActivity : AppCompatActivity() {

    // Used to run the ML model
    private lateinit var tflite: Interpreter
    // Back button at the top of the screen
    private lateinit var btnBack: TextView

    // Minimum and maximum values used to adjust inputs for the model
    private val featureMin = floatArrayOf(15.0f, 1.0f, 0.5f)
    private val featureMax = floatArrayOf(35.0f, 12.0f, 2.5f)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_analytics)

        // Sets up back button to return to previous screen
        btnBack = findViewById(R.id.btnBack)
        btnBack.setOnClickListener { navigateBack() }

        // UI elements that show overall stats
        val tvTotalVehicles = findViewById<TextView>(R.id.tvTotalVehicles)
        val tvAveragePressure = findViewById<TextView>(R.id.tvAveragePressure)
        val tvAttention = findViewById<TextView>(R.id.tvAttention)

        // Layout where vehicle cards will be added
        val llTyresContainer = findViewById<LinearLayout>(R.id.llTyresContainer)

        // Loads vehicle data from local file
        val vehicles = loadVehiclesFromJson()

        // Load the ML model
        tflite = Interpreter(loadModelFile())

        var needingAttentionCount = 0
        var totalPressure = 0.0

        // Loops through each vehicle and checks tyre condition
        vehicles.forEach { vehicle ->
            totalPressure += vehicle.tyrePressure

            // Converts string values into numeric float values
            val tyreAgeFloat = vehicle.tyreAge.replace("[^\\d.]".toRegex(), "").toFloatOrNull() ?: 0f
            val loadCapacityFloat = vehicle.loadCapacity.replace("[^\\d.]".toRegex(), "").toFloatOrNull() ?: 0f

            // Normalizes values so the model can understand them
            val normTyrePressure = (vehicle.tyrePressure.toFloat() - featureMin[0]) / (featureMax[0] - featureMin[0])
            val normTyreAge = (tyreAgeFloat - featureMin[1]) / (featureMax[1] - featureMin[1])
            val normLoadCapacity = (loadCapacityFloat - featureMin[2]) / (featureMax[2] - featureMin[2])

            // Sets up input and output for the model
            val input = arrayOf(floatArrayOf(normTyrePressure, normTyreAge, normLoadCapacity))
            val output = Array(1) { FloatArray(1) }

            // Runs the model to get prediction
            tflite.run(input, output)
            val confidence = output[0][0]

            // Decides condition based on model result
            val (emoji, remark) = when {
                confidence >= 0.7f -> "🟢" to "Your tyre is healthy"
                confidence >= 0.4f -> "🟡" to "Check tyre soon"
                else -> {
                    needingAttentionCount++
                    Toast.makeText(
                        this,
                        "${vehicle.name} needs tyre replacement! Confidence: ${"%.2f".format(confidence)}",
                        Toast.LENGTH_LONG
                    ).show()
                    "🔴" to "Tyre is critical, take action"
                }
            }

            // Creates card to display the result for each vehicle
            val cardView = CardView(this).apply {
                layoutParams = LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
                ).apply {
                    setMargins(16, 16, 16, 16)
                }
                radius = 12f
                cardElevation = 6f
                setContentPadding(24, 24, 24, 24)
                setCardBackgroundColor(android.graphics.Color.parseColor("#D3D3D3"))
            }

            // Horizontal layout for emoji and text
            val llHorizontal = LinearLayout(this).apply {
                orientation = LinearLayout.HORIZONTAL
                gravity = Gravity.CENTER_VERTICAL
            }

            // Emoji to show tyre condition
            val tvEmoji = TextView(this).apply {
                text = emoji
                textSize = 20f
                layoutParams = LinearLayout.LayoutParams(120, 120)
                gravity = Gravity.CENTER
            }

            // Container for name, confidence, and remark
            val llTexts = LinearLayout(this).apply {
                orientation = LinearLayout.VERTICAL
                layoutParams = LinearLayout.LayoutParams(
                    0,
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    1f
                )
                setPadding(16, 0, 0, 0)
            }

            // Shows vehicle name
            val tvName = TextView(this).apply {
                text = vehicle.name
                textSize = 16f
                setTypeface(null, Typeface.BOLD)
                setTextColor(android.graphics.Color.BLACK)
            }

            // Shows model prediction confidence
            val tvConfidence = TextView(this).apply {
                text = "Confidence: %.2f".format(confidence)
                textSize = 14f
                setTextColor(android.graphics.Color.BLACK)
            }

            // Shows remark based on condition
            val tvRemark = TextView(this).apply {
                text = remark
                textSize = 14f
                setTextColor(android.graphics.Color.BLACK)
            }

            // Adds all texts to the text container
            llTexts.addView(tvName)
            llTexts.addView(tvConfidence)
            llTexts.addView(tvRemark)
            // Adds emoji and texts to horizontal layout
            llHorizontal.addView(tvEmoji)
            llHorizontal.addView(llTexts)
            // Adds layout to card
            cardView.addView(llHorizontal)
            // Adds card to the main container
            llTyresContainer.addView(cardView)
        }

        // Shows overall statistics
        tvTotalVehicles.text = "• Total Vehicles: ${vehicles.size}"
        val avgPressure = if (vehicles.isNotEmpty()) totalPressure / vehicles.size else 0.0
        tvAveragePressure.text = "• Average Tyre Pressure: ${"%.1f".format(avgPressure)} PSI"
        tvAttention.text = "• Vehicles Needing Attention: $needingAttentionCount !"
    }

    // Goes back to the previous screen
    private fun navigateBack() {
        val intent = Intent(this, MainMenuActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
        startActivity(intent)
        finish()
    }

    // Handles device back button
    override fun onBackPressed() {
        navigateBack()
    }

    // Loads the model file from assets
    private fun loadModelFile(): MappedByteBuffer {
        val fileDescriptor = assets.openFd("vehicle_model.tflite")
        val inputStream = FileInputStream(fileDescriptor.fileDescriptor)
        val fileChannel = inputStream.channel
        val startOffset = fileDescriptor.startOffset
        val declaredLength = fileDescriptor.declaredLength
        return fileChannel.map(FileChannel.MapMode.READ_ONLY, startOffset, declaredLength)
    }

    // Loads vehicle data from a local file in assets folder
    private fun loadVehiclesFromJson(): List<Vehicle> {
        val jsonString = assets.open("vehicles.json")
            .bufferedReader()
            .use { it.readText() }
        val listType = object : TypeToken<List<Vehicle>>() {}.type
        return Gson().fromJson(jsonString, listType)
    }
}
