package com.example.pokeapi.services


import com.example.pokeapi.models.example.test.models.PokemonTestModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @GET("pokemon/{id}")
    suspend fun getPokemonById(@Path("id") id: String): Response<PokemonTestModel>
}
