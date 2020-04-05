package com.serdnito.pokeapi.data.api.model

import com.serdnito.pokeapi.domain.model.PokemonType
import com.google.gson.annotations.SerializedName

data class PokemonType(
    @SerializedName("slot")
    val slot: Int,
    @SerializedName("type")
    val type: NamedApiResource
) {

    fun mapToDomain() =
        PokemonType(type.getId(), type.name)

}