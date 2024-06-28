package com.example.valorant

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AgentViewModel : ViewModel() {
    private val _playableAgents: MutableLiveData<List<Agent>> = MutableLiveData()
    val playableAgents: LiveData<List<Agent>> get() = _playableAgents

    init {
        fetchAgents()
    }

    private fun fetchAgents() {
        viewModelScope.launch(Dispatchers.IO) {
            val response = RetrofitInstance.api.getPlayableAgents()
            if (response.isSuccessful) {
                _playableAgents.postValue(response.body()?.data ?: emptyList())
            } else {
                _playableAgents.postValue(emptyList())
            }
        }
    }
}
