package com.nickwlaw.pokedex.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import coil.annotation.ExperimentalCoilApi
import com.nickwlaw.pokedex.ui.composables.PokeCard
import com.nickwlaw.pokedex.viewmodel.PokemonListViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

@ExperimentalCoilApi
@ExperimentalFoundationApi
class PokemonListFragment : Fragment() {
    private val viewModel: PokemonListViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = ComposeView(requireContext()).apply {
        setContent {
            val pokemon by viewModel.pokemonList.collectAsState(listOf())
            val lazyGridState = rememberLazyListState()
            LazyVerticalGrid(
                cells = GridCells.Fixed(2),
                state = lazyGridState
            ) {
                items(pokemon) { mon ->
                    PokeCard(mon)
                }
            }
        }
    }
}
