package com.example.pokeapi

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.pokeapi.databinding.ActivityMainBinding
import com.example.pokeapi.viewmodel.PokemonViewModel
import com.squareup.picasso.Picasso
import kotlinx.coroutines.launch


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel : PokemonViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        lifecycleScope.launch {
            viewModel.pokemonData.collect { pokemonModel ->
                pokemonModel?.let { pokemonData ->
                    val pokemonSprite = pokemonData.sprites.frontDefault
                    Picasso.get().load(pokemonSprite).into(binding.imageViewPokemonSprite)
                    binding.txtPokemonName.text = pokemonData.name
                }
            }
        }

        lifecycleScope.launch {
            viewModel.error.collect { messageError ->
                messageError?.let {
                    Toast.makeText(this@MainActivity, it, Toast.LENGTH_LONG).show()
                }
            }
        }

        binding.btnRandom.setOnClickListener {
            val randomNumber: Int = (1..151).shuffled().first()
            viewModel.getPokemonById(randomNumber.toString())
        }
    }
}
