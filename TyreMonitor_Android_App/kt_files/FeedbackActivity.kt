package com.example.tyremonitor

import android.content.Intent
import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.RatingBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class FeedbackActivity : AppCompatActivity() {

    private lateinit var btnBack: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_feedback)

        btnBack = findViewById(R.id.btnBack)
        btnBack.setOnClickListener { navigateBack() }

        val etFeedback = findViewById<EditText>(R.id.etFeedback)
        val ratingBar = findViewById<RatingBar>(R.id.ratingBar)
        val btnSubmit = findViewById<Button>(R.id.btnSubmitFeedback)

        ratingBar.progressTintList = ColorStateList.valueOf(Color.parseColor("#FFD700"))
        ratingBar.secondaryProgressTintList = ColorStateList.valueOf(Color.parseColor("#FFD700"))
        ratingBar.progressBackgroundTintList = ColorStateList.valueOf(Color.parseColor("#CCCCCC"))

        btnSubmit.setOnClickListener {
            val feedback = etFeedback.text.toString().trim()
            val rating = ratingBar.rating

            if (feedback.isEmpty()) {
                Toast.makeText(this, "Please enter your feedback", Toast.LENGTH_SHORT).show()
            } else {

                Toast.makeText(
                    this,
                    "Thank you for your feedback!\nRating: $rating stars",
                    Toast.LENGTH_SHORT
                ).show()

                etFeedback.text.clear()
                ratingBar.rating = 0f
            }
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
