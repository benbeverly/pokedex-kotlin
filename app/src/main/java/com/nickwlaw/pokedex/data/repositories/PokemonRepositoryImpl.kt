package com.nickwlaw.pokedex.data.repositories

import android.util.Log
import com.nickwlaw.pokedex.data.models.domain.Pokemon
import com.nickwlaw.pokedex.data.models.mappers.toDomain
import com.nickwlaw.pokedex.data.network.PokemonAPI
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*

class PokemonRepositoryImpl(private val pokemonApi: PokemonAPI) : PokemonRepository {

    private val pokemonListCache = mutableSetOf<Pokemon>()

    override fun getPokemonList(): Flow<Set<Pokemon>> = flow {
        val resourceListResponse = pokemonApi.getPokemonResourceList()

        if (resourceListResponse.isSuccessful) {
            Log.d(TAG, resourceListResponse.body().toString())

            resourceListResponse.body()?.let { response ->
                response.results.map { namedResource ->
                    getPokemon(namedResource.name)?.let { mon ->
                        pokemonListCache.add(mon)
                        emit(pokemonListCache)
                    }
                }
            }
        }

    }.shareIn(
        scope = CoroutineScope(Dispatchers.Default),
        started = SharingStarted.Lazily,
        replay = 1
    )


    private suspend fun getPokemon(name: String): Pokemon? {
        val pokemonResponse = pokemonApi.getPokemon(name)

        return if (pokemonResponse.isSuccessful) {
            Log.d(TAG, pokemonResponse.body().toString())

            pokemonResponse.body()?.toDomain()
        } else {
            Log.d(TAG, "Error fetching Pokemon: ${pokemonResponse.errorBody().toString()}")
            null
        }
    }

    companion object {
        const val TAG = "PokemonRepo"
    }
}
