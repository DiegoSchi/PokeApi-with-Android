package com.example.pokeapi.models.example.test.models


import com.google.gson.annotations.SerializedName

data class Cries(
    @SerializedName("latest")
    val latest: String,
    @SerializedName("legacy")
    val legacy: String
)