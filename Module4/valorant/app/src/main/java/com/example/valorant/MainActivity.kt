package com.example.valorant

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    private val viewModel: AgentViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.layoutManager = GridLayoutManager(this, 2)

        val adapter = AgentAdapter { agent ->
            val intent = Intent(this, DetailActivity::class.java)
            intent.putExtra("agent", agent)
            startActivity(intent)
        }
        recyclerView.adapter = adapter

        viewModel.playableAgents.observe(this, Observer { agents ->
            adapter.submitList(agents)
        })
    }
}
