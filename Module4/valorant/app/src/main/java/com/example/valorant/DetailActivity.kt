package com.example.valorant

import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide

class DetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.detail)

        val imageView = findViewById<ImageView>(R.id.imageView)
        val nameTextView = findViewById<TextView>(R.id.nameTextView)
        val descriptionTextView = findViewById<TextView>(R.id.descriptionTextView)
        val abilitiesContainer = findViewById<LinearLayout>(R.id.abilitiesContainer)

        val button: ImageButton = findViewById(R.id.backButton)
        button.setOnClickListener{
            val intent2 = Intent(this@DetailActivity, MainActivity::class.java)
            startActivity(intent2)}

        val agent = if (android.os.Build.VERSION.SDK_INT >= 33) {
            intent.getParcelableExtra("agent", Agent::class.java)
        } else {
            @Suppress("DEPRECATION")
            intent.getParcelableExtra<Agent>("agent")
        }

        if (agent != null) {
            nameTextView.text = agent.displayName
            descriptionTextView.text = agent.description
            Glide.with(this).load(agent.displayIcon).into(imageView)

            for (ability in agent.abilities) {
                val abilityView = layoutInflater.inflate(R.layout.ability_item, abilitiesContainer, false)
                val abilityNameTextView = abilityView.findViewById<TextView>(R.id.abilityNameTextView)
                val abilityDescriptionTextView = abilityView.findViewById<TextView>(R.id.abilityDescriptionTextView)
                val abilityIconImageView = abilityView.findViewById<ImageView>(R.id.abilityIconImageView)

                abilityNameTextView.text = ability.displayName
                abilityDescriptionTextView.text = ability.description
                if (ability.displayIcon != null) {
                    Glide.with(this).load(ability.displayIcon).into(abilityIconImageView)
                }

                abilitiesContainer.addView(abilityView)
            }
        }
    }
}

