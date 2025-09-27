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
import com.google.android.material.snackbar.Snackbar

class UsagePolicyActivity : AppCompatActivity() {

    private lateinit var btnBack: Button
    private lateinit var btnCopyPolicy: MaterialButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_usage_policy)

        btnBack = findViewById(R.id.btnBack)

        btnBack.setOnClickListener {
            navigateBack()
        }

        btnCopyPolicy = findViewById(R.id.btnCopyPolicy)

        btnCopyPolicy.setOnClickListener {
            copyPolicyToClipboard()
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

    private fun copyPolicyToClipboard() {
        val policyText = """
            1. Introduction
            Welcome to our app. By accessing or using this application, you agree to comply with this Usage Policy. Please read carefully.

            2. User Conduct
            - Users must not misuse the app or interfere with its normal operation.
            - No unauthorized access or hacking attempts.
            - Respect intellectual property rights of content provided.

            3. Privacy
            - We may collect non-personal data for improving app performance.
            - Personal information will never be sold or shared without consent.
            - Users should avoid sharing sensitive personal data within the app.

            4. Content Usage
            - Content within the app is for personal use only.
            - Redistribution or modification without permission is prohibited.
            - Users are responsible for the content they upload, if applicable.

            5. Prohibited Actions
            - Posting offensive, abusive, or illegal content.
            - Using the app for fraudulent or commercial purposes without approval.
            - Attempting to bypass security measures.

            6. Account Responsibility
            - Keep login credentials secure.
            - Notify support immediately in case of unauthorized access.
            - We are not responsible for losses due to compromised accounts.

            7. Limitation of Liability
            - The app is provided 'as is' without warranties.
            - We are not liable for indirect, incidental, or consequential damages.
            - Users assume all responsibility for using the app.

            8. Changes to Policy
            - We may update this policy at any time.
            - Users are encouraged to review this page periodically.
            - Continued use constitutes acceptance of changes.

            9. Contact
            For any questions or concerns, please contact support@example.com.
            Thank you for using our app responsibly. Enjoy your experience!
        """.trimIndent()

        val clipboard = getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
        val clip = ClipData.newPlainText("Usage Policy", policyText)
        clipboard.setPrimaryClip(clip)

        Toast.makeText(this, "Policy copied to clipboard", Toast.LENGTH_SHORT).show()
    }
}
