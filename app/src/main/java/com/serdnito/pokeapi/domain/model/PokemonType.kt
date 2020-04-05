package com.serdnito.pokeapi.domain.model

class PokemonType(
    val id: Int,
    val name: String
) {

    fun getColor() = PokemonTypeColor.getById(id)

}