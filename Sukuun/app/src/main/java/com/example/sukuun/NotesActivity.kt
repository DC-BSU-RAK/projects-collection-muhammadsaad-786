package com.example.sukuun

import android.content.Context
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class NotesActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notes)

        val etGratitude = findViewById<EditText>(R.id.etGratitude)
        val btnSave = findViewById<Button>(R.id.btnSaveNote)
        val tvNoteHistory = findViewById<TextView>(R.id.tvNoteHistory)
        val btnBack = findViewById<ImageButton>(R.id.btnBack)

        val sharedPref = getSharedPreferences("SukuunPrefs", Context.MODE_PRIVATE)

        // 1. Load all previous logs on start
        tvNoteHistory.text = sharedPref.getString("all_notes", "Your journey begins here...")

        // 2. Back Button Logic
        btnBack.setOnClickListener { finish() }

        // 3. Save Logic (Appends new note to history)
        btnSave.setOnClickListener {
            val newNote = etGratitude.text.toString().trim()

            if (newNote.isNotEmpty()) {
                val timestamp = SimpleDateFormat("MMM dd, yyyy - HH:mm", Locale.getDefault()).format(Date())
                val existingNotes = sharedPref.getString("all_notes", "")

                // Add new note at the top
                val updatedNotes = "✨ $timestamp\n$newNote\n\n────────────────\n$existingNotes"

                sharedPref.edit().putString("all_notes", updatedNotes).apply()

                // UI Updates
                tvNoteHistory.text = updatedNotes
                etGratitude.text.clear()
                Toast.makeText(this, "Reflection saved", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Write something first...", Toast.LENGTH_SHORT).show()
            }
        }
    }
}