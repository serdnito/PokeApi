package com.serdnito.pokeapi.data.api.model

import com.serdnito.pokeapi.domain.model.Pokemon
import com.google.gson.annotations.SerializedName

data class Pokemon(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("types")
    val types: List<PokemonType>,
    @SerializedName("sprites")
    val sprites: PokemonSprites
) {

    fun mapToDomain() =
        Pokemon(id, name, id, types.map { it.mapToDomain() }.reversed(), sprites.frontDefault)

}