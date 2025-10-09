package com.example.tyremonitor

// Imports libraries
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

// Activity class for the "About" screen of the app
class AboutAppActivity : AppCompatActivity() {

    // Declares UI elements
    private lateinit var btnBack: Button
    private lateinit var btnCopy: MaterialButton
    private lateinit var tvAboutContent: TextView

    // Called when the activity is created
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_about_app)

        // Initializes UI elements by linking them to layout IDs
        btnBack = findViewById(R.id.btnBack)
        btnCopy = findViewById(R.id.btnCopyAbout)
        tvAboutContent = findViewById(R.id.tvAboutContent)

        // It handles back button click [navigate back to Main Menu]
        btnBack.setOnClickListener {
            navigateBack()
        }

        // It handles copy button click [copy text content to clipboard]
        btnCopy.setOnClickListener {
            val clipboard = getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
            val clip = ClipData.newPlainText("About TyreMonitor", tvAboutContent.text)
            clipboard.setPrimaryClip(clip)
            Toast.makeText(this, "Content copied to clipboard", Toast.LENGTH_SHORT).show()
        }
    }

    // Function to navigate back to Main Menu activity
    private fun navigateBack() {
        val intent = Intent(this, MainMenuActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
        startActivity(intent)
        finish()
    }

    // Handles system back button
    override fun onBackPressed() {
        navigateBack()
    }
}
