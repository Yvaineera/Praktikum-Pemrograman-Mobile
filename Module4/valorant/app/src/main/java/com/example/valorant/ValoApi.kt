package com.example.valorant

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ValoApi {
    @GET("v1/agents")
    suspend fun getPlayableAgents(@Query("isPlayableCharacter") isPlayable: Boolean = true): Response<AgentResponse>
}