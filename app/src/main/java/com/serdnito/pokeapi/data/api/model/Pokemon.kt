package com.serdnito.pokeapi.data.api.model

import com.google.gson.annotations.SerializedName
import com.serdnito.pokeapi.domain.model.Pokemon

data class Pokemon(
    @SerializedName("abilities")
    val abilities: List<PokemonAbility>,
    @SerializedName("height")
    val height: Int, // decimetres
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
    val weight: Int // hectograms
) {

    fun mapToDomain() =
        Pokemon(
            abilities.map { it.mapToDomain() },
            height / 10.0,
            id,
            name,
            stats.map { it.mapToDomain() },
            types.map { it.mapToDomain() }.reversed(),
            sprites.frontDefault,
            weight / 10.0
        )

}