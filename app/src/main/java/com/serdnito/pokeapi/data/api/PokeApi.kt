package com.serdnito.pokeapi.data.api

import com.serdnito.pokeapi.data.api.model.NamedApiResourceList
import com.serdnito.pokeapi.data.api.model.Pokemon
import io.reactivex.Flowable
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PokeApi {

    @GET("pokemon")
    fun getPokedex(
        @Query("offset") offset: Int,
        @Query("limit") limit: Int
    ): Single<NamedApiResourceList>

    @GET("pokemon/{id}")
    fun getPokemon(@Path("id") id: Int): Flowable<Pokemon>

}