package com.serdnito.pokeapi.data.api.model

import com.serdnito.pokeapi.domain.model.Pokemon
import com.google.gson.annotations.SerializedName

data class Pokemon(
    @SerializedName("height")
    val height: Int,
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("sprites")
    val sprites: PokemonSprites,
    @SerializedName("stats")
    val stats: List<PokemonStat>,
    @SerializedName("types")
    val types: List<PokemonType>,
    @SerializedName("weight")
    val weight: Int
) {

    fun mapToDomain() =
        Pokemon(
            height,
            id,
            name,
            stats.map { it.mapToDomain() },
            types.map { it.mapToDomain() }.reversed(),
            sprites.frontDefault,
            weight
        )

}