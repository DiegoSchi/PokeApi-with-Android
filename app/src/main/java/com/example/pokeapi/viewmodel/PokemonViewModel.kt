package com.example.pokeapi.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pokeapi.models.example.test.models.PokemonTestModel
import com.example.pokeapi.repository.PokemonRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class PokemonViewModel: ViewModel () {

    private val repository = PokemonRepository()

    private val _pokemonData = MutableStateFlow<PokemonTestModel?>(null)
    val pokemonData : StateFlow<PokemonTestModel?> get() = _pokemonData

    private val _error = MutableStateFlow<String?>(null)
    val error : StateFlow<String?> get() =  _error

    fun getPokemonById(id: String) {
        viewModelScope.launch {
            try {
                val response = repository.getPokemonById(id)
                _pokemonData.value = response.body()
            } catch (e: Exception) {
                _error.value = e.message
            }
        }
    }
}