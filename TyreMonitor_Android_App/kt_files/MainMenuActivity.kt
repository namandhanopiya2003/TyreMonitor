package com.example.tyremonitor

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.card.MaterialCardView

class MainMenuActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_menu)

        val cardVehicles = findViewById<MaterialCardView>(R.id.cardVehicles)
        cardVehicles.setOnClickListener {
            startActivity(Intent(this, VehiclesListActivity::class.java))
        }

        val cardProfile = findViewById<MaterialCardView>(R.id.cardProfile)
        cardProfile.setOnClickListener {
            startActivity(Intent(this, ProfileActivity::class.java))
        }

        val cardUsagePolicy = findViewById<MaterialCardView>(R.id.cardUsagePolicy)
        cardUsagePolicy.setOnClickListener {
            startActivity(Intent(this, UsagePolicyActivity::class.java))
        }

        val cardSummary = findViewById<MaterialCardView>(R.id.cardSummary)
        cardSummary.setOnClickListener {
            startActivity(Intent(this, AnalyticsActivity::class.java))
        }

        val cardMaintenance = findViewById<MaterialCardView>(R.id.cardMaintenance)
        cardMaintenance.setOnClickListener {
            startActivity(Intent(this, MaintenanceGuidelinesActivity::class.java))
        }

        val cardHelp = findViewById<MaterialCardView>(R.id.cardHelp)
        cardHelp.setOnClickListener {
            startActivity(Intent(this, HelpActivity::class.java))
        }

        val cardAbout = findViewById<MaterialCardView>(R.id.cardAbout)
        cardAbout.setOnClickListener {
            startActivity(Intent(this, AboutAppActivity::class.java))
        }

        val cardFeedback = findViewById<MaterialCardView>(R.id.cardFeedback)
        cardFeedback.setOnClickListener {
            startActivity(Intent(this, FeedbackActivity::class.java))
        }

        val cardLogout = findViewById<MaterialCardView>(R.id.cardLogout)
        cardLogout.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }
    }
}
