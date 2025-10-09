package com.example.tyremonitor

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class HelpActivity : AppCompatActivity() {

    // Button to go back to the main menu
    private lateinit var btnBack: Button

    // Texts that show the answers
    private lateinit var tvAnswer1: TextView
    private lateinit var tvAnswer2: TextView
    private lateinit var tvAnswer3: TextView
    private lateinit var tvAnswer4: TextView

    // Texts that serve as questions
    private lateinit var tvQuestion1: TextView
    private lateinit var tvQuestion2: TextView
    private lateinit var tvQuestion3: TextView
    private lateinit var tvQuestion4: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_help)

        // Sets up back button to return to previous screen
        btnBack = findViewById(R.id.btnBack)
        btnBack.setOnClickListener { navigateBack() }

        // Initializes answer text views
        tvAnswer1 = findViewById(R.id.tvAnswer1)
        tvAnswer2 = findViewById(R.id.tvAnswer2)
        tvAnswer3 = findViewById(R.id.tvAnswer3)
        tvAnswer4 = findViewById(R.id.tvAnswer4)

        // Initializes question text views
        tvQuestion1 = findViewById(R.id.tvQuestion1)
        tvQuestion2 = findViewById(R.id.tvQuestion2)
        tvQuestion3 = findViewById(R.id.tvQuestion3)
        tvQuestion4 = findViewById(R.id.tvQuestion4)

        // Sets up click behavior [tap a question to show/hide its answer]
        setToggle(tvQuestion1, tvAnswer1)
        setToggle(tvQuestion2, tvAnswer2)
        setToggle(tvQuestion3, tvAnswer3)
        setToggle(tvQuestion4, tvAnswer4)
    }

    // Shows or hides the answer when the user taps a question
    private fun setToggle(question: TextView, answer: TextView) {
        question.setOnClickListener {
            if (answer.visibility == View.GONE) {
                answer.visibility = View.VISIBLE
            } else {
                answer.visibility = View.GONE
            }
        }
    }

    // Goes back to the main menu screen
    private fun navigateBack() {
        val intent = Intent(this, MainMenuActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
        startActivity(intent)
        finish()
    }

    // Handles back button press on device
    override fun onBackPressed() {
        navigateBack()
    }
}
