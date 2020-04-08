package com.serdnito.pokeapi.data.datasource.pokemon

import com.serdnito.pokeapi.data.api.PokeApi
import com.serdnito.pokeapi.domain.model.NamedResourceList
import com.serdnito.pokeapi.domain.model.Pokemon
import com.serdnito.pokeapi.domain.model.Species
import io.reactivex.Flowable
import io.reactivex.Single
import javax.inject.Inject

class PokemonRemoteDataSource @Inject constructor(
    private val api: PokeApi
) {

    fun getPokedex(offset: Int, limit: Int): Single<NamedResourceList> =
        api.getPokedex(offset, limit)
            .map { it.mapToDomain() }

    fun getPokemon(id: Int): Flowable<Pokemon> =
        api.getPokemon(id)
            .map { it.mapToDomain() }

    fun getSpecies(id: Int): Single<Species> =
        api.getSpecies(id)
            .map { it.mapToDomain() }

}