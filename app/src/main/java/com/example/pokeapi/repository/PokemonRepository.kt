package com.example.pokeapi.repository

import com.example.pokeapi.services.ApiService
import com.example.pokeapi.services.RetrofitClient

class PokemonRepository {
    private val api = RetrofitClient.instance.create(ApiService::class.java)
    suspend fun getPokemonById(id: String) = api.getPokemonById(id)
}