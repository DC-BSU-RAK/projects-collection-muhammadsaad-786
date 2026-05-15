package com.example.sukuun

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        Handler(Looper.getMainLooper()).postDelayed({
            // Move to SignupActivity after 2 seconds
            val intent = Intent(this, SignupActivity::class.java)
            startActivity(intent)
            finish()
        }, 2000)
    }
}