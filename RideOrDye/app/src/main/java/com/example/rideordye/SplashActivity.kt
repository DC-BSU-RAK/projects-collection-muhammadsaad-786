package com.example.rideordye

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        val logo = findViewById<ImageView>(R.id.appLogo)

        // Simple Fade In Animation
        logo.alpha = 0f
        logo.animate().alpha(1f).setDuration(1500).start()

        // 3 Second Delay (3000 milliseconds)
        Handler(Looper.getMainLooper()).postDelayed({
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish() // Prevents user from going back to splash
        }, 3000)
    }
}