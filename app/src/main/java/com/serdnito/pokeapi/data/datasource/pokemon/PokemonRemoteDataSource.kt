package com.serdnito.pokeapi.data.datasource.pokemon

import com.serdnito.pokeapi.data.api.PokeApi
import javax.inject.Inject

class PokemonRemoteDataSource @Inject constructor(
    private val api: PokeApi
) {

    fun getPokedex(offset: Int, limit: Int) =
        api.getPokedex(offset, limit)
            .map { it.mapToDomain() }

    fun getPokemon(id: Int) =
        api.getPokemon(id)
            .map { it.mapToDomain() }

}