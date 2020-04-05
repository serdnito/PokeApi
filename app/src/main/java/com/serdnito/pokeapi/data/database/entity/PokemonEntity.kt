package com.serdnito.pokeapi.data.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.serdnito.pokeapi.domain.model.Pokemon

@Entity(
    tableName = "pokemon"
)
class PokemonEntity(
    @PrimaryKey val id: Int,
    @ColumnInfo val name: String,
    @ColumnInfo val order: Int,
    @ColumnInfo(name = "url_front_sprite") val urlFrontSprite: String
) {

    fun mapToDomain(types: List<PokemonTypeEntity>) =
        Pokemon(id, name, order, types.map { it.mapToDomain() }, urlFrontSprite)

}