package com.example.pokeapi.models.example.test.models


import com.google.gson.annotations.SerializedName

data class OfficialArtwork(
    @SerializedName("front_default")
    val frontDefault: String,
    @SerializedName("front_shiny")
    val frontShiny: String
)