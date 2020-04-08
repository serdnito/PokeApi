package com.serdnito.pokeapi.data.api.model

import android.annotation.SuppressLint
import com.google.gson.annotations.SerializedName
import com.serdnito.pokeapi.domain.model.PokedexEntry

class FlavorText(
    @SerializedName("flavor_text")
    val flavorText: String,
    @SerializedName("language")
    val language: NamedApiResource,
    @SerializedName("version")
    val version: NamedApiResource
) {

    @SuppressLint("DefaultLocale")
    fun mapToDomain() = PokedexEntry(flavorText.capitalize(), version.name.capitalize())

}