package com.nickwlaw.pokedex.viewmodel

import androidx.lifecycle.*
import com.nickwlaw.pokedex.data.models.domain.Pokemon
import com.nickwlaw.pokedex.data.repositories.PokemonRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class PokemonListViewModel(
    pokeRepo: PokemonRepository
) : ViewModel() {

    /**
     * In a Clean Architecture, we might turn this into a UseCase / Intereactor to perform the sort
     * on the Pokemon data set
     */
    val pokemonList: Flow<List<Pokemon>> =
        pokeRepo.getPokemonList().map { mons -> mons.sortedBy { mon -> mon.id } }
}
