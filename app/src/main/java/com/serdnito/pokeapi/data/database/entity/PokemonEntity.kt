package com.serdnito.pokeapi.data.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.serdnito.pokeapi.domain.model.Pokemon

@Entity(
    tableName = "pokemon"
)
class PokemonEntity(
    @ColumnInfo val height: Double,
    @PrimaryKey val id: Int,
    @ColumnInfo val name: String,
    @ColumnInfo(name = "species_id") val speciesId: Int,
    @ColumnInfo(name = "url_front_sprite") val urlFrontSprite: String,
    @ColumnInfo val weight: Double
) {

    companion object {
        fun mapFromDomain(pokemon: Pokemon) =
            PokemonEntity(
                pokemon.height,
                pokemon.id,
                pokemon.name,
                pokemon.speciesId,
                pokemon.urlFrontSprite,
                pokemon.weight
            )
    }

    fun mapToDomain(
        abilities: List<PokemonAbilityEntity>,
        types: List<TypeEntity>,
        stats: List<PokemonStatEntity>
    ) =
        Pokemon(
            abilities.map { it.mapToDomain() },
            height,
            id,
            name,
            speciesId,
            stats.map { it.mapToDomain() },
            types.map { it.mapToDomain() },
            urlFrontSprite,
            weight
        )

}