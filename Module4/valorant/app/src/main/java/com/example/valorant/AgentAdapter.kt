package com.example.valorant

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class AgentAdapter(private val onClick: (Agent) -> Unit) :
    ListAdapter<Agent, AgentAdapter.AgentViewHolder>(AgentDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AgentViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.agent_item, parent, false)
        return AgentViewHolder(view, onClick)
    }

    override fun onBindViewHolder(holder: AgentViewHolder, position: Int) {
        val agent = getItem(position)
        holder.bind(agent)
    }

    class AgentViewHolder(itemView: View, val onClick: (Agent) -> Unit) :
        RecyclerView.ViewHolder(itemView) {
        private val displayIconImageView: ImageView = itemView.findViewById(R.id.displayIcon)
        private var currentAgent: Agent? = null

        init {
            itemView.setOnClickListener {
                currentAgent?.let {
                    onClick(it)
                }
            }
        }

        fun bind(agent: Agent) {
            currentAgent = agent
            Glide.with(itemView.context)
                .load(agent.displayIcon)
                .into(displayIconImageView)
        }
    }
}

class AgentDiffCallback : DiffUtil.ItemCallback<Agent>() {
    override fun areItemsTheSame(oldItem: Agent, newItem: Agent): Boolean {
        return oldItem.displayName == newItem.displayName
    }

    override fun areContentsTheSame(oldItem: Agent, newItem: Agent): Boolean {
        return oldItem == newItem
    }
}
