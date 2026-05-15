package com.example.sukuun

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class SignupActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)

        // Finding views by the IDs we set in the XML
        val etName = findViewById<EditText>(R.id.etName)
        val btnSignup = findViewById<Button>(R.id.btnSignup)
        val sharedPref = getSharedPreferences("SukuunPrefs", Context.MODE_PRIVATE)

        btnSignup.setOnClickListener {
            val name = etName.text.toString().trim()

            if (name.isNotEmpty()) {
                sharedPref.edit().putString("user_name", name).apply()
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish()
            } else {
                Toast.makeText(this, "Please enter your name", Toast.LENGTH_SHORT).show()
            }
        }
    }
}