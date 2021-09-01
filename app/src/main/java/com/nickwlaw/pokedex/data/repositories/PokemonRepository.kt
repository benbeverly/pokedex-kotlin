package com.nickwlaw.pokedex.data.repositories

import com.nickwlaw.pokedex.data.models.domain.Pokemon
import kotlinx.coroutines.flow.Flow

interface PokemonRepository {
    fun getPokemonList(): Flow<Set<Pokemon>>
}
