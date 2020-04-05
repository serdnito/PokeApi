package com.serdnito.pokeapi.data.api.model

import com.google.gson.annotations.SerializedName

class PokemonSprites(
    @SerializedName("front_default")
    val frontDefault: String
)