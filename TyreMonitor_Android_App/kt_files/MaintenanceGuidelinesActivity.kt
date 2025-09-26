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

    private lateinit var btnBack: Button
    private lateinit var btnCopyGuidelines: MaterialButton
    private lateinit var tvGuidelinesContent: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maintenance_guidelines)

        btnBack = findViewById(R.id.btnBack)
        btnCopyGuidelines = findViewById(R.id.btnCopyGuidelines)
        tvGuidelinesContent = findViewById(R.id.tvGuidelinesContent)

        btnBack.setOnClickListener {
            navigateBack()
        }

        btnCopyGuidelines.setOnClickListener {
            val clipboard = getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
            val clip = ClipData.newPlainText("Tyre Guidelines", tvGuidelinesContent.text)
            clipboard.setPrimaryClip(clip)
            Toast.makeText(this, "Guidelines copied to clipboard", Toast.LENGTH_SHORT).show()
        }
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
