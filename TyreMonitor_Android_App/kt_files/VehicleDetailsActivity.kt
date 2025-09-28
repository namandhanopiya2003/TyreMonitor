package com.example.tyremonitor

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ProgressBar
import android.widget.RatingBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class VehicleDetailsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_vehicle_details)

        val tvName = findViewById<TextView>(R.id.tvVehicleName)
        val tvReg = findViewById<TextView>(R.id.tvVehicleReg)
        val tvPressure = findViewById<TextView>(R.id.tvTyrePressure)
        val tvAge = findViewById<TextView>(R.id.tvAge)
        val tvLoad = findViewById<TextView>(R.id.tvLoad)

        val ratingAge = findViewById<RatingBar>(R.id.ratingAge)
        val progressLoad = findViewById<ProgressBar>(R.id.progressLoad)

        val btnBack = findViewById<Button>(R.id.btnBack)
        btnBack.setOnClickListener {
            startActivity(Intent(this, VehiclesListActivity::class.java))
            finish()
        }

        val vehicle = intent.getSerializableExtra("vehicle") as? Vehicle

        vehicle?.let {
            tvName.text = "Name: ${it.name}"
            tvReg.text = "• Reg No: ${it.regNo}"
            tvPressure.text = "• Tyre Pressure: ${it.tyrePressure} PSI"
            tvAge.text = "• Tyre Age: ${it.tyreAge} years"
            tvLoad.text = "• Load Capacity: ${it.loadCapacity} tonnes"

            val age = it.tyreAge.toFloatOrNull() ?: 0f
            val stars = (5f - age).coerceIn(0f, 5f)
            ratingAge.rating = stars

            val load = it.loadCapacity.toFloatOrNull() ?: 0f
            progressLoad.max = 20
            progressLoad.progress = (load * 10).toInt()
        }
    }
}
