package com.serdnito.pokeapi.data.database.entity

import androidx.room.ColumnInfo
import com.serdnito.pokeapi.domain.model.PokemonStat
import com.serdnito.pokeapi.domain.model.Stat

class PokemonStatEntity(
    @ColumnInfo(name = "stat_id") val statId: Int,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "max") val max: Int,
    @ColumnInfo(name = "value") val statValue: Int
) {

    fun mapToDomain() = PokemonStat(max, name, Stat.getById(statId), statValue)

}