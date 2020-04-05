package com.serdnito.pokeapi.data.api.model

import com.serdnito.pokeapi.domain.model.NamedResourceList
import com.google.gson.annotations.SerializedName

class NamedApiResourceList(
    @SerializedName("count")
    val count: Int,
    @SerializedName("next")
    val next: String?,
    @SerializedName("previous")
    val previous: String?,
    @SerializedName("results")
    val results: List<NamedApiResource>
) {

    fun mapToDomain() =
        NamedResourceList(
            list = results.map { it.mapToDomain() }
        )

}