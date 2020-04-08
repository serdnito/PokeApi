package com.serdnito.pokeapi.data.api.model

import com.google.gson.annotations.SerializedName

class Genus(
    @SerializedName("genus")
    val genus: String,
    @SerializedName("language")
    val language: NamedApiResource
)