package com.serdnito.pokeapi.data.api.model

import com.google.gson.annotations.SerializedName
import com.serdnito.pokeapi.domain.model.PokemonAbility

class PokemonAbility(
    @SerializedName("is_hidden")
    val isHidden: Boolean,
    @SerializedName("slot")
    val slot: Int,
    @SerializedName("ability")
    val ability: NamedApiResource
) {

    fun mapToDomain() = PokemonAbility(
        ability.url.substringBeforeLast("/").substringAfterLast("/").toInt(),
        isHidden,
        ability.name
    )

}