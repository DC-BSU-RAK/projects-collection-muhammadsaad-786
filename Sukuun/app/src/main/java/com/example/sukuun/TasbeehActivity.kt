package com.example.sukuun

import android.content.Context
import android.os.Bundle
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView

class TasbeehActivity : AppCompatActivity() {
    private var count = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tasbeeh)

        val tvCounter = findViewById<TextView>(R.id.tvCounter)
        val circleTapArea = findViewById<CardView>(R.id.circleTapArea)
        val btnBack = findViewById<ImageButton>(R.id.btnBack)

        // Load saved count from SharedPreferences
        val sharedPref = getSharedPreferences("SukuunPrefs", Context.MODE_PRIVATE)
        count = sharedPref.getInt("count_key", 0)
        tvCounter.text = count.toString()

        // 1. Back Button Logic (Now correctly inside onCreate)
        btnBack.setOnClickListener {
            finish()
        }

        // 2. Tap the Circle to increment
        circleTapArea.setOnClickListener {
            count++
            tvCounter.text = count.toString()
            sharedPref.edit().putInt("count_key", count).apply()
        }

        // 3. Long press the Circle to reset
        circleTapArea.setOnLongClickListener {
            count = 0
            tvCounter.text = "0"
            sharedPref.edit().putInt("count_key", 0).apply()
            true
        }
    }
}