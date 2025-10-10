package com.example.tyremonitor

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.button.MaterialButton
import android.widget.TextView

class MaintenanceGuidelinesActivity : AppCompatActivity() {

    // Button that takes the user back to the main menu
    private lateinit var btnBack: Button
    // Button that copies the text of the maintenance guidelines
    private lateinit var btnCopyGuidelines: MaterialButton
    // Text view that shows the maintenance guidelines on screen
    private lateinit var tvGuidelinesContent: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maintenance_guidelines)

        // It gets the buttons and text view from the layout
        btnBack = findViewById(R.id.btnBack)
        btnCopyGuidelines = findViewById(R.id.btnCopyGuidelines)
        tvGuidelinesContent = findViewById(R.id.tvGuidelinesContent)

        // When the Back button is clicked, it goes back to the main menu screen
        btnBack.setOnClickListener {
            navigateBack()
        }

        // When the Copy button is clicked, it copies the guidelines text to clipboard
        btnCopyGuidelines.setOnClickListener {
            val clipboard = getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
            val clip = ClipData.newPlainText("Tyre Guidelines", tvGuidelinesContent.text)
            clipboard.setPrimaryClip(clip)
            Toast.makeText(this, "Guidelines copied to clipboard", Toast.LENGTH_SHORT).show()
        }
    }

    // This function takes the user back to the main menu screen
    private fun navigateBack() {
        val intent = Intent(this, MainMenuActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
        startActivity(intent)
        finish()
    }

    // When the device back button is pressed, it goes back to main menu
    override fun onBackPressed() {
        navigateBack()
    }
}
