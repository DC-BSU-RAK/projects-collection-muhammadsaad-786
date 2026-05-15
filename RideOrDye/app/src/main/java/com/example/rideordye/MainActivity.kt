package com.example.rideordye // Ensure this matches your package name

import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    // These variables store the "calculation" state
    private var selectedColorId: Int? = null
    private var selectedWheelId: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Setup Color Buttons
        setupColorButton(R.id.color1, "Blue")
        setupColorButton(R.id.color2, "Green")
        setupColorButton(R.id.color3, "Purple")
        setupColorButton(R.id.color4, "Red")

        // Setup Wheel Buttons
        setupWheelButton(R.id.adventurebutton, "Adventure")
        setupWheelButton(R.id.turbinewheel, "Turbine")
        setupWheelButton(R.id.spokebutton, "Spoke")

        // Info Button
        findViewById<ImageButton>(R.id.btnInfo).setOnClickListener {
            AlertDialog.Builder(this)
                .setTitle("How to Use")
                .setMessage("1. Select a color.\n2. Choose your wheels.\n3. Tap Generate to see your creation!")
                .setPositiveButton("Close", null)
                .show()
        }

        // Calculator Logic: Generate Button
        findViewById<Button>(R.id.btnGenerate).setOnClickListener {
            if (selectedColorId == null || selectedWheelId == null) {
                // Validation: Show error if inputs are incomplete
                Toast.makeText(this, "Please select both a color and a wheel option first!", Toast.LENGTH_SHORT).show()
            } else {
                // Input is valid, run the calculation
                showGeneratePopup(selectedColorId!!, selectedWheelId!!)
            }
        }
    }

    private fun setupColorButton(id: Int, colorName: String) {
        findViewById<ImageButton>(id).setOnClickListener {
            selectedColorId = id
            Toast.makeText(this, "$colorName color selected", Toast.LENGTH_SHORT).show()
        }
    }

    private fun setupWheelButton(id: Int, wheelName: String) {
        findViewById<ImageButton>(id).setOnClickListener {
            selectedWheelId = id
            Toast.makeText(this, "$wheelName wheels selected", Toast.LENGTH_SHORT).show()
        }
    }

    private fun showGeneratePopup(colorId: Int, wheelId: Int) {
        val dialogView = LayoutInflater.from(this).inflate(R.layout.popup_generate, null)
        val popupImage = dialogView.findViewById<ImageView>(R.id.popupImageView)
        val closeButton = dialogView.findViewById<Button>(R.id.btnClose)

        // Map the inputs to the specific image
        val resultImage = getResultImage(colorId, wheelId)
        popupImage.setImageResource(resultImage)

        val dialog = AlertDialog.Builder(this).setView(dialogView).create()
        closeButton.setOnClickListener { dialog.dismiss() }
        dialog.show()
    }

    private fun getResultImage(colorId: Int, wheelId: Int): Int {
        // This is your logic table for all 12 combinations
        return when (colorId) {
            R.id.color1 -> when (wheelId) { // Blue
                R.id.adventurebutton -> R.drawable.blue_adventure
                R.id.turbinewheel -> R.drawable.blue_turbine
                else -> R.drawable.blue_spoke
            }
            R.id.color2 -> when (wheelId) { // Green
                R.id.adventurebutton -> R.drawable.green_adventure
                R.id.turbinewheel -> R.drawable.green_turbine
                else -> R.drawable.green_spoke
            }
            R.id.color3 -> when (wheelId) { // Purple
                R.id.adventurebutton -> R.drawable.purple_adventure
                R.id.turbinewheel -> R.drawable.purple_turbine
                else -> R.drawable.purple_spoke
            }
            else -> when (wheelId) { // Red
                R.id.adventurebutton -> R.drawable.red_adventure
                R.id.turbinewheel -> R.drawable.red_turbine
                else -> R.drawable.red_spoke
            }
        }
    }
}