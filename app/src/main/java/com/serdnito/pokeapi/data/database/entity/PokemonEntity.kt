package com.serdnito.pokeapi.data.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.serdnito.pokeapi.domain.model.Pokemon

@Entity(
    tableName = "pokemon"
)
class PokemonEntity(
    @ColumnInfo val height: Int,
    @PrimaryKey val id: Int,
    @ColumnInfo val name: String,
    @ColumnInfo(name = "url_front_sprite") val urlFrontSprite: String,
    @ColumnInfo val weight: Int
) {

    companion object {
        fun mapFromDomain(pokemon: Pokemon) =
            PokemonEntity(
                pokemon.height,
                pokemon.id,
                pokemon.name,
                pokemon.urlFrontSprite,
                pokemon.weight
            )
    }

    fun mapToDomain(types: List<TypeEntity>, stats: List<PokemonStatEntity>) =
        Pokemon(
            height,
            id,
            name,
            stats.map { it.mapToDomain() },
            types.map { it.mapToDomain() },
            urlFrontSprite,
            weight
        )

}