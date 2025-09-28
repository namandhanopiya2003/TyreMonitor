package com.example.tyremonitor

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class VehiclesListActivity : AppCompatActivity() {

    private lateinit var rvVehicles: RecyclerView
    private lateinit var btnAnalytics: Button
    private lateinit var btnProfile: Button
    private lateinit var btnBack: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_vehicles_list)

        rvVehicles = findViewById(R.id.rvVehicles)
        btnAnalytics = findViewById(R.id.btnAnalytics)
        btnProfile = findViewById(R.id.btnProfile)
        btnBack = findViewById(R.id.btnBack)

        val vehicles = loadVehiclesFromJson()

        rvVehicles.layoutManager = LinearLayoutManager(this)
        rvVehicles.adapter = VehicleAdapter(this, vehicles)

        btnBack.setOnClickListener {
            navigateBack()
        }

        btnAnalytics.setOnClickListener {
            startActivity(Intent(this, AnalyticsActivity::class.java))
        }

        btnProfile.setOnClickListener {
            startActivity(Intent(this, ProfileActivity::class.java))
        }
    }

    private fun loadVehiclesFromJson(): List<Vehicle> {
        val jsonString = assets.open("vehicles.json")
            .bufferedReader()
            .use { it.readText() }

        val listType = object : TypeToken<List<Vehicle>>() {}.type
        return Gson().fromJson(jsonString, listType)
    }

    private fun navigateBack() {
        val intent = Intent(this, MainMenuActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
        startActivity(intent)
        finish()
    }

    override fun onBackPressed() {
        navigateBack()
    }
}
