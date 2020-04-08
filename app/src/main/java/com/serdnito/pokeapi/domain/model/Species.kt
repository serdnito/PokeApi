package com.serdnito.pokeapi.domain.model

class Species(
    val color: String,
    val eggGroups: List<String>,
    val entries: List<PokedexEntry>,
    val genus: String,
    val generation: String,
    val id: Int,
    val shape: String
)