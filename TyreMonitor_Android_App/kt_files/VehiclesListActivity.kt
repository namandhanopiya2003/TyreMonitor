package com.example.tyremonitor

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

// This screen shows a list of all vehicles using a RecyclerView
class VehiclesListActivity : AppCompatActivity() {

    // Buttons and list from the layout
    private lateinit var rvVehicles: RecyclerView
    private lateinit var btnAnalytics: Button
    private lateinit var btnProfile: Button
    private lateinit var btnBack: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_vehicles_list)

        // Connects the UI elements to their IDs in the layout file
        rvVehicles = findViewById(R.id.rvVehicles)
        btnAnalytics = findViewById(R.id.btnAnalytics)
        btnProfile = findViewById(R.id.btnProfile)
        btnBack = findViewById(R.id.btnBack)

        // Loads the vehicle data from a local file
        val vehicles = loadVehiclesFromJson()

        rvVehicles.layoutManager = LinearLayoutManager(this)
        rvVehicles.adapter = VehicleAdapter(this, vehicles)

        // When back button is clicked, it goes back to the main menu
        btnBack.setOnClickListener {
            navigateBack()
        }

        // Opens the Analytics screen when the Analytics button is clicked
        btnAnalytics.setOnClickListener {
            startActivity(Intent(this, AnalyticsActivity::class.java))
        }

        // Opens the Profile screen when the Profile button is clicked
        btnProfile.setOnClickListener {
            startActivity(Intent(this, ProfileActivity::class.java))
        }
    }

    // Reads a local file from the assets folder and turns it into a list of Vehicle objects
    private fun loadVehiclesFromJson(): List<Vehicle> {
        val jsonString = assets.open("vehicles.json")
            .bufferedReader()
            .use { it.readText() }

        // Converts the text of file into a list of Vehicle objects
        val listType = object : TypeToken<List<Vehicle>>() {}.type
        return Gson().fromJson(jsonString, listType)
    }
    
    // Takes back to the MainMenu screen
    private fun navigateBack() {
        val intent = Intent(this, MainMenuActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
        startActivity(intent)
        finish()
    }

    // If the user presses the device back button, it goes back to the main menu
    override fun onBackPressed() {
        navigateBack()
    }
}
