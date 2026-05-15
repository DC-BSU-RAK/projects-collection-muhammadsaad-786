package com.example.sukuun

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity

class DuaActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dua)

        findViewById<ImageButton>(R.id.btnBack).setOnClickListener { finish() }

        // Logic for specific Dua links
        setupDuaButton(R.id.btnDuaMorning, "https://www.duas.org/mobile/morning-evening-duas.html")
        setupDuaButton(R.id.btnDuaEating, "https://www.duas.org/mobile/dua-eating-drinking.html")
        setupDuaButton(R.id.btnDuaSleeping, "https://www.duas.org/mobile/dua-before-sleeping.html")
        setupDuaButton(R.id.btnDuaAnxiety, "https://www.duas.org/mobile/duas-for-anxiety.html")
    }

    private fun setupDuaButton(buttonId: Int, url: String) {
        findViewById<Button>(buttonId).setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
            startActivity(intent)
        }
    }
}