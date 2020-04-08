package com.serdnito.pokeapi.data.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.serdnito.pokeapi.domain.model.PokedexEntry
import com.serdnito.pokeapi.domain.model.Species

@Entity(
    tableName = "species"
)
class SpeciesEntity(
    @ColumnInfo val color: String,
    @ColumnInfo(name = "egg_groups") val eggGroups: String,
    @ColumnInfo val entries: String,
    @ColumnInfo val genus: String,
    @ColumnInfo val generation: String,
    @PrimaryKey val id: Int,
    @ColumnInfo val shape: String
) {

    companion object {
        fun mapFromDomain(species: Species) =
            SpeciesEntity(
                species.color,
                species.eggGroups.joinToString("$$"),
                species.entries.joinToString("$$") { "${it.game}&&${it.entry}" },
                species.genus,
                species.generation,
                species.id,
                species.shape
            )
    }

    fun mapToDomain() =
        Species(
            color,
            eggGroups.split("$$"),
            entries.split("$$").map {
                val fullEntry = it.split("&&")
                PokedexEntry(fullEntry[0], fullEntry[1])
            },
            genus,
            generation,
            id,
            shape
        )

}