package com.serdnito.pokeapi.data.api.model

import com.google.gson.annotations.SerializedName
import com.serdnito.pokeapi.domain.model.PokemonStat
import com.serdnito.pokeapi.domain.model.Stat

class PokemonStat(
    @SerializedName("base_stat")
    val baseStat: Int,
    @SerializedName("stat")
    val stat: NamedApiResource
) {

    fun mapToDomain(): PokemonStat {
        val id = stat.url.substringBeforeLast("/").substringAfterLast("/").toInt()
        return PokemonStat(
            calculateMax(id),
            stat.name,
            Stat.getById(id),
            baseStat
        )
    }

    private fun calculateMax(statId: Int) =
        if (statId == 1) { // HP
            baseStat * 2 + 204
        } else {
            ((baseStat * 2 + 99) * 1.1).toInt()
        }

}