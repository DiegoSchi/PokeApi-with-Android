package com.example.pokeapi

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.pokeapi.databinding.ActivityMainBinding
import com.example.pokeapi.services.ApiService
import com.squareup.picasso.Picasso
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.btnRandom.setOnClickListener {
            val randomNumber: Int = (1..151).shuffled().first()
            getPokemonById(randomNumber.toString())
        }
    }

    private fun getRetrofitInstance(): Retrofit {
        return Retrofit.Builder().baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    private fun getPokemonById(id: String) {
        lifecycleScope.launch(Dispatchers.IO) {
            Log.d("CoroutineDebug", "Llamada a la API iniciada en el hilo: ${Thread.currentThread().name}")

            try {
                val call = getRetrofitInstance().create(ApiService::class.java).getPokemonById(id)
                val pokemon = call.body()

                Log.d("CoroutineDebug", "Respuesta recibida: $pokemon")

                runOnUiThread {
                    Log.d("CoroutineDebug", "Actualizando la UI en el hilo: ${Thread.currentThread().name}")
                    if (call.isSuccessful && pokemon != null) {
                        val pokemonSprite = pokemon.sprites.frontDefault
                        Picasso.get().load(pokemonSprite).into(binding.imageViewPokemonSprite)
                        binding.txtPokemonName.text = pokemon.name
                    } else {
                        showError()
                    }
                }
            } catch (e: Exception) {
                Log.e("CoroutineDebug", "Error: ${e.message}", e)
            }
        }
    }



    private fun showError() {
        Toast.makeText(this, "Error", Toast.LENGTH_LONG).show()
    }

    companion object {
        private const val BASE_URL: String = "https://pokeapi.co/api/v2/"
    }
}
