package com.serdnito.pokeapi.data.api.model

import com.serdnito.pokeapi.domain.model.NamedResource
import com.google.gson.annotations.SerializedName

data class NamedApiResource(
    @SerializedName("name")
    val name: String,
    @SerializedName("url")
    val url: String
) {

    fun getId() = url.substringBeforeLast("/").substringAfterLast("/").toInt()

    fun mapToDomain() =
        NamedResource(getId(), name)

}