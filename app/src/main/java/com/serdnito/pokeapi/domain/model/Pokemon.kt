package com.serdnito.pokeapi.domain.model

class Pokemon(
    val height: Int,
    val id: Int,
    val name: String,
    val stats: List<PokemonStat>,
    val types: List<PokemonType>,
    val urlFrontSprite: String,
    val weight: Int
)