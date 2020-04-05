package com.serdnito.pokeapi.data.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.serdnito.pokeapi.domain.model.PokemonType

@Entity(
    tableName = "pokemon_type"
)
class PokemonTypeEntity(
    @PrimaryKey val id: Int,
    @ColumnInfo val name: String
) {

    fun mapToDomain() = PokemonType(id, name)

}