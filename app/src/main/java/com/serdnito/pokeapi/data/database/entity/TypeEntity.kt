package com.serdnito.pokeapi.data.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.serdnito.pokeapi.domain.model.PokemonType

@Entity(
    tableName = "types"
)
class TypeEntity(
    @PrimaryKey val id: Int,
    @ColumnInfo val name: String
) {

    companion object {
        fun mapFromDomain(types: List<PokemonType>) =
            types.map { TypeEntity(it.id, it.name) }
    }

    fun mapToDomain() = PokemonType(id, name)

}