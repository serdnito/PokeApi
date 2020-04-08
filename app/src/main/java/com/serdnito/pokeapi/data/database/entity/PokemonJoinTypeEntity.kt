package com.serdnito.pokeapi.data.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import com.serdnito.pokeapi.domain.model.Pokemon

@Entity(
    tableName = "pokemon_type_join",
    primaryKeys = ["pokemon_id", "type_id"],
    foreignKeys = [
        ForeignKey(
            entity = PokemonEntity::class,
            parentColumns = ["id"],
            childColumns = ["pokemon_id"]
        ),
        ForeignKey(
            entity = TypeEntity::class,
            parentColumns = ["id"],
            childColumns = ["type_id"]
        )
    ]
)
class PokemonJoinTypeEntity(
    @ColumnInfo(name = "pokemon_id") val pokemonId: Int,
    @ColumnInfo(name = "type_id") val typeId: Int,
    @ColumnInfo(name = "is_primary") val isPrimary: Int
) {

    companion object {
        fun mapFromDomain(pokemon: Pokemon) =
            pokemon.types.mapIndexed { index, pokemonType ->
                PokemonJoinTypeEntity(
                    pokemon.id,
                    pokemonType.id,
                    if (pokemon.types.size == 1 || (pokemon.types.size > 1 && index != 0)) 1 else 0
                )
            }
    }

}