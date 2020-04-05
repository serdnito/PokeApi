package com.serdnito.pokeapi.domain.model

enum class PokemonTypeColor(
    val id: Int,
    val bgHexColor: String = "#FFFFFF",
    val textHexColor: String = "#121212"
) {
    Normal(1, "#DEECE1"), // Pastel smirk
    Fighting(2, "#DEA5A4"), // Pastel pink
    Flying(3, "#99C5C4"), // Pastel turquoise
    Poison(4, "#CA9BF7"), // Baby purple
    Ground(5, "#E9D1BF"), // Pastel rose tan
    Rock(6, "#836953"), // Pastel brown
    Bug(7, "#BEE7A5"), // Pastel pea
    Ghost(8, "#BDB0D0"), // Pastel lilac
    Steel(9, "#CFCFC4"), // Pastel grey
    Fire(10, "#FF964F"), // Pastel orange
    Water(11, "#A2BFFE"), // Pastel blue
    Grass(12, "#77DD77"), // Pastel green
    Electric(13, "#FDDE6C"), // Pale gold
    Psychic(14, "#D8A1C4"), // Pastel lavender
    Ice(15, "#D6FFFE"), // Pale blue
    Dragon(16, "#FF6961"), // Pastel red
    Dark(17, "#AA9499"), // Pastel persian
    Fairy(18, "#FFB7CE"), // Baby pink
    Unknown(10001),
    Shadow(10002);

    companion object {
        fun getById(id: Int) = values().find { it.id == id } ?: Unknown
    }

}