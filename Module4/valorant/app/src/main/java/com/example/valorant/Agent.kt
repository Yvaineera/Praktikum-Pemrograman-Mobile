package com.example.valorant

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

data class AgentResponse(
    val data: List<Agent>
)

@Parcelize
data class Ability(
    val displayName: String,
    val description: String,
    val displayIcon: String?
) : Parcelable

@Parcelize
data class Agent(
    val uuid: String,
    val displayName: String,
    val description: String,
    val displayIcon: String,
    val abilities: List<Ability>
) : Parcelable
