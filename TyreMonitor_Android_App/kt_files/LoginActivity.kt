package com.example.tyremonitor

import android.content.Intent
import android.os.Bundle
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class LoginActivity : AppCompatActivity() {

    private var isPasswordVisible = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val etUsername = findViewById<EditText>(R.id.etUsername)
        val etPassword = findViewById<EditText>(R.id.etPassword)
        val btnLogin = findViewById<Button>(R.id.btnLogin)
        val btnGuestLogin = findViewById<Button>(R.id.btnGuestLogin)
        val tvForgotPassword = findViewById<TextView>(R.id.tvForgotPassword)
        val ivTogglePassword = findViewById<ImageView>(R.id.ivTogglePassword)

        btnLogin.setOnClickListener {
            val username = etUsername.text.toString().trim()
            val password = etPassword.text.toString().trim()

            if (username == "user" && password == "1234") {
                val intent = Intent(this, MainMenuActivity::class.java)
                intent.putExtra("USER_TYPE", "Admin")
                startActivity(intent)
                finish()
            } else {
                Toast.makeText(this, "Invalid Credentials", Toast.LENGTH_SHORT).show()
            }
        }

        btnGuestLogin.setOnClickListener {
            Toast.makeText(this, "Logged in as Guest", Toast.LENGTH_SHORT).show()
            val intent = Intent(this, MainMenuActivity::class.java)
            intent.putExtra("USER_TYPE", "Guest")
            startActivity(intent)
            finish()
        }

        tvForgotPassword.setOnClickListener {
            val username = etUsername.text.toString().trim()

            if (username.isEmpty()) {
                Toast.makeText(this, "Please enter username", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(
                    this,
                    "Password reset link sent to your registered email",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }

        ivTogglePassword.setOnClickListener {
            isPasswordVisible = !isPasswordVisible
            if (isPasswordVisible) {
                etPassword.transformationMethod = HideReturnsTransformationMethod.getInstance()
                ivTogglePassword.setImageResource(R.drawable.ic_visibility)
            } else {
                etPassword.transformationMethod = PasswordTransformationMethod.getInstance()
                ivTogglePassword.setImageResource(R.drawable.ic_visibility_off)
            }
            etPassword.setSelection(etPassword.text.length)
        }
    }
}
