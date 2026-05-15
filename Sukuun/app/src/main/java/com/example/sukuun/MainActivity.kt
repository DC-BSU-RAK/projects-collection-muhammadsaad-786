package com.example.sukuun

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // 1. Personalized Greeting
        val sharedPref = getSharedPreferences("SukuunPrefs", Context.MODE_PRIVATE)
        val userName = sharedPref.getString("user_name", "User") ?: "User"
        val tvTitle = findViewById<TextView>(R.id.tvTitle)
        tvTitle.text = "Hello, $userName"

        // 2. Random Daily Ayah Logic
        setupDailyQuote()

        // 3. Navigation Listeners
        findViewById<Button>(R.id.btnTasbeeh).setOnClickListener {
            startActivity(Intent(this, TasbeehActivity::class.java))
        }

        findViewById<Button>(R.id.btnNotes).setOnClickListener {
            startActivity(Intent(this, NotesActivity::class.java))
        }

        findViewById<Button>(R.id.btnDua).setOnClickListener {
            startActivity(Intent(this, DuaActivity::class.java))
        }

        findViewById<Button>(R.id.btnReadQuran).setOnClickListener {
            val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse("https://quran.com"))
            startActivity(browserIntent)
        }

        findViewById<Button>(R.id.btnInstructions).setOnClickListener {
            showInstructionsDialog()
        }

        // Show instructions on very first launch
        showInstructionsDialog()
    }

    private fun setupDailyQuote() {
        val quotes = listOf(
            "“So verily, with every hardship, there is ease.”" to "Surah Ash-Sharh",
            "“Be patient; indeed, the promise of Allah is truth.”" to "Surah Ar-Rum",
            "“My success is only by Allah.”" to "Surah Hud",
            "“He knows what is in every heart.”" to "Surah Al-Mulk",
            "“Allah does not burden a soul beyond that it can bear.”" to "Surah Al-Baqarah"
        )
        val (randomQuote, source) = quotes.random()
        findViewById<TextView>(R.id.tvDailyQuote).text = randomQuote
        findViewById<TextView>(R.id.tvQuoteSource).text = "— $source"
    }

    private fun showInstructionsDialog() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("How to use Sukuun")
        builder.setMessage("• Tasbeeh: Tap circle to count, hold to reset.\n\n" +
                "• Gratitude: Save your daily reflections.\n\n" +
                "• Resources: Read Quran or find Duas for peace.")
        builder.setPositiveButton("Got it!") { dialog, _ -> dialog.dismiss() }
        builder.create().show()
    }
}