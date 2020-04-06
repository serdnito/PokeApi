package com.serdnito.pokeapi.data.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.serdnito.pokeapi.domain.model.PokemonStat

@Entity(
    tableName = "stats"
)
class StatEntity(
    @PrimaryKey val id: Int,
    @ColumnInfo val name: String
) {

    companion object {
        fun mapFromDomain(stats: List<PokemonStat>) =
            stats.map { StatEntity(it.id, it.name) }
    }

}