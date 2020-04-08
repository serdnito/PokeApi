package com.serdnito.pokeapi.data.api.model

import android.annotation.SuppressLint
import com.google.gson.annotations.SerializedName
import com.serdnito.pokeapi.domain.model.PokemonAbility

class PokemonAbility(
    @SerializedName("is_hidden")
    val isHidden: Boolean,
    @SerializedName("ability")
    val ability: NamedApiResource
) {

    @SuppressLint("DefaultLocale")
    fun mapToDomain() = PokemonAbility(
        ability.url.substringBeforeLast("/").substringAfterLast("/").toInt(),
        isHidden,
        ability.name.decapitalize()
    )

}