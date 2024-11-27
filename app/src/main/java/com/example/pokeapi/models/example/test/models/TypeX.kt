package com.example.pokeapi.models.example.test.models


import com.google.gson.annotations.SerializedName

data class TypeX(
    @SerializedName("name")
    val name: String,
    @SerializedName("url")
    val url: String
)