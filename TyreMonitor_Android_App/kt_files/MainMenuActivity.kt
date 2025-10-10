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

        // When the "Vehicles" card is clicked, go to the Vehicles List screen
        val cardVehicles = findViewById<MaterialCardView>(R.id.cardVehicles)
        cardVehicles.setOnClickListener {
            startActivity(Intent(this, VehiclesListActivity::class.java))
        }

        // When the "Profile" card is clicked, go to the Profile screen
        val cardProfile = findViewById<MaterialCardView>(R.id.cardProfile)
        cardProfile.setOnClickListener {
            startActivity(Intent(this, ProfileActivity::class.java))
        }

        // When the "Usage Policy" card is clicked, go to the Usage Policy screen
        val cardUsagePolicy = findViewById<MaterialCardView>(R.id.cardUsagePolicy)
        cardUsagePolicy.setOnClickListener {
            startActivity(Intent(this, UsagePolicyActivity::class.java))
        }

        // When the "Summary" card is clicked, go to the Analytics or Summary screen
        val cardSummary = findViewById<MaterialCardView>(R.id.cardSummary)
        cardSummary.setOnClickListener {
            startActivity(Intent(this, AnalyticsActivity::class.java))
        }

        // When the "Maintenance" card is clicked, go to the Maintenance Guidelines screen
        val cardMaintenance = findViewById<MaterialCardView>(R.id.cardMaintenance)
        cardMaintenance.setOnClickListener {
            startActivity(Intent(this, MaintenanceGuidelinesActivity::class.java))
        }

        // When the "Help" card is clicked, go to the Help or FAQ screen
        val cardHelp = findViewById<MaterialCardView>(R.id.cardHelp)
        cardHelp.setOnClickListener {
            startActivity(Intent(this, HelpActivity::class.java))
        }

        // When the "About" card is clicked, go to the About App screen
        val cardAbout = findViewById<MaterialCardView>(R.id.cardAbout)
        cardAbout.setOnClickListener {
            startActivity(Intent(this, AboutAppActivity::class.java))
        }

        // When the "Feedback" card is clicked, go to the Feedback screen
        val cardFeedback = findViewById<MaterialCardView>(R.id.cardFeedback)
        cardFeedback.setOnClickListener {
            startActivity(Intent(this, FeedbackActivity::class.java))
        }

        // When the "Logout" card is clicked, go back to the Login screen
        val cardLogout = findViewById<MaterialCardView>(R.id.cardLogout)
        cardLogout.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }
    }
}
