package com.serdnito.pokeapi.domain.repository

import com.serdnito.pokeapi.domain.model.NamedResourceList
import com.serdnito.pokeapi.domain.model.Pokemon
import com.serdnito.pokeapi.domain.model.Species
import io.reactivex.Flowable
import io.reactivex.Single

interface PokemonRepository {
    fun getPokedex(offset: Int, limit: Int): Single<NamedResourceList>
    fun getPokemon(id: Int): Flowable<Pokemon>
    fun getSpecies(id: Int): Single<Species>
}