package com.serdnito.pokeapi.data.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.serdnito.pokeapi.domain.model.NamedResource
import com.serdnito.pokeapi.domain.model.NamedResourceList

@Entity(
    tableName = "pokedex"
)
class PokedexItemEntity(
    @PrimaryKey val id: Int,
    @ColumnInfo val name: String
) {

    fun mapToDomain() = NamedResource(id, name)

}

fun List<PokedexItemEntity>.mapToDomain() =
    NamedResourceList(map { it.mapToDomain() })