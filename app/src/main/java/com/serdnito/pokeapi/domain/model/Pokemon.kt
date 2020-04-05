package com.serdnito.pokeapi.domain.model

class Pokemon(
    val id: Int,
    val name: String,
    val order: Int,
    val types: List<PokemonType>,
    val urlFrontSprite: String
)