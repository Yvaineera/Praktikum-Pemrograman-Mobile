package com.example.tipcalculator

import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import java.text.NumberFormat
import kotlin.math.ceil

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val calculateButton: Button = findViewById(R.id.calculate_button)
        calculateButton.setOnClickListener { calculateTip() }
    }

    private fun calculateTip() {
        val costEditText: EditText = findViewById(R.id.cost_of_service_edit_text)
        val costString = costEditText.text.toString()

        // Check if costString is empty
        if (costString.isEmpty()) {
            displayTip(0.0)
            return
        }

        val cost = costString.toDoubleOrNull()

        // Handle invalid input
        if (cost == null || cost == 0.0) {
            displayTip(0.0)
            return
        }

        val tipPercentage = when (findViewById<RadioGroup>(R.id.tip_options).checkedRadioButtonId) {
            R.id.amazing_option -> 0.20
            R.id.good_option -> 0.18
            else -> 0.15
        }

        var tip = tipPercentage * cost
        val roundUp = findViewById<Switch>(R.id.round_up_switch).isChecked
        if (roundUp) {
            tip = ceil(tip)
        }

        displayTip(tip)
    }

    private fun displayTip(tip: Double) {
        val formattedTip = NumberFormat.getCurrencyInstance().format(tip)
        val tipResult: TextView = findViewById(R.id.tip_result)
        tipResult.text = getString(R.string.tip_amount, formattedTip)
    }
}