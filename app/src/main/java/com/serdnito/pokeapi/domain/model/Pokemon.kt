package com.serdnito.pokeapi.domain.model

class Pokemon(
    val abilities: List<PokemonAbility>,
    val height: Double,
    val id: Int,
    val name: String,
    val speciesId: Int,
    val stats: List<PokemonStat>,
    val types: List<PokemonType>,
    val urlFrontSprite: String,
    val weight: Double
)