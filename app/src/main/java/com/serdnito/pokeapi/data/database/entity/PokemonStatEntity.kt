package com.serdnito.pokeapi.data.database.entity

import androidx.room.ColumnInfo
import com.serdnito.pokeapi.domain.model.PokemonStat

class PokemonStatEntity(
    @ColumnInfo(name = "stat_id") val statId: Int,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "stat_value") val statValue: Int
) {

    fun mapToDomain() = PokemonStat(statId, name, statValue)

}