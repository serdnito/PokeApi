package com.serdnito.pokeapi.data.api.model

import com.google.gson.annotations.SerializedName
import com.serdnito.pokeapi.domain.model.PokemonStat

class PokemonStat(
    @SerializedName("base_stat")
    val baseStat: Int,
    @SerializedName("stat")
    val stat: NamedApiResource
) {

    fun mapToDomain() =
        PokemonStat(
            stat.url.substringBeforeLast("/").substringAfterLast("/").toInt(),
            stat.name,
            baseStat
        )

}