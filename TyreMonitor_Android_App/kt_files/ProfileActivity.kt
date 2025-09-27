package com.example.tyremonitor

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class ProfileActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        val btnBack = findViewById<Button>(R.id.btnBack)
        btnBack.setOnClickListener { finish() }

        val btnUsagePolicy = findViewById<Button>(R.id.btnUsagePolicy)
        val btnHelpFAQ = findViewById<Button>(R.id.btnHelpFAQ)
        val btnAboutApp = findViewById<Button>(R.id.btnAboutApp)
        val btnLogout = findViewById<Button>(R.id.btnLogout)

        btnUsagePolicy.setOnClickListener {
            startActivity(Intent(this, UsagePolicyActivity::class.java))
        }

        btnHelpFAQ.setOnClickListener {
            startActivity(Intent(this, HelpActivity::class.java))
        }

        btnAboutApp.setOnClickListener {
            startActivity(Intent(this, AboutAppActivity::class.java))
        }

        btnLogout.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }

        val tvName = findViewById<TextView>(R.id.tvName)
        val etName = findViewById<EditText>(R.id.etName)
        val tvRole = findViewById<TextView>(R.id.tvRole)
        val etRole = findViewById<EditText>(R.id.etRole)
        val tvContact = findViewById<TextView>(R.id.tvContact)
        val etContact = findViewById<EditText>(R.id.etContact)
        val btnEdit = findViewById<Button>(R.id.btnEdit)

        btnEdit.setOnClickListener {
            if (etName.visibility == View.GONE) {
                etName.setText("")
                etRole.setText("")
                etContact.setText("")

                etName.visibility = View.VISIBLE
                etRole.visibility = View.VISIBLE
                etContact.visibility = View.VISIBLE

                btnEdit.text = "Save"
            } else {
                if (etName.text.isNotEmpty()) tvName.text = "Name: ${etName.text}"
                if (etRole.text.isNotEmpty()) tvRole.text = "Role: ${etRole.text}"
                if (etContact.text.isNotEmpty()) tvContact.text = "Contact: ${etContact.text}"

                etName.visibility = View.GONE
                etRole.visibility = View.GONE
                etContact.visibility = View.GONE

                btnEdit.text = "Edit"
            }
        }
    }
}
