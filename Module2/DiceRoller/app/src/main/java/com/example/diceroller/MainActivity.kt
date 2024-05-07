package com.example.diceroller

import android.os.Bundle
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.diceroller.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val btnroll = binding.btnroll
        btnroll.setOnClickListener {
            rollDice()
        }

        val dice1 = binding.dice1
        dice1.setImageResource(R.drawable.empty_dice)
        val dice2 = binding.dice2
        dice2.setImageResource(R.drawable.empty_dice)
    }

    private fun rollDice(){
        val dice1 = Dice(6)
        val result1 = dice1.roll()

        val dice2 = Dice(6)
        val result2 = dice2.roll()

        updateDiceImage(binding.dice1, result1)
        updateDiceImage(binding.dice2, result2)

        if (result1==result2){
            Toast.makeText(this, "Selamat Anda dapat dadu double!", Toast.LENGTH_SHORT).show()
        } else{
            Toast.makeText(this, "Anda belum beruntung!", Toast.LENGTH_SHORT).show()
        }

    }

    private fun updateDiceImage(imageView: ImageView, diceRollResult: Int) {
        when (diceRollResult) {
            1 -> imageView.setImageResource(R.drawable.dice_1)
            2 -> imageView.setImageResource(R.drawable.dice_2)
            3 -> imageView.setImageResource(R.drawable.dice_3)
            4 -> imageView.setImageResource(R.drawable.dice_4)
            5 -> imageView.setImageResource(R.drawable.dice_5)
            6 -> imageView.setImageResource(R.drawable.dice_6)
        }
    }
}

class Dice(val side: Int){
    fun roll(): Int {
        return (1..side).random()
    }
}
