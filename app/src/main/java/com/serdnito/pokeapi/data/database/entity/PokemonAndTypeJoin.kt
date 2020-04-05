package com.serdnito.pokeapi.data.database.entity

import androidx.room.Entity
import androidx.room.ForeignKey

@Entity(
    tableName = "pokemon_type_join",
    primaryKeys = ["pokemonId", "typeId"],
    foreignKeys = [
        ForeignKey(
            entity = PokemonEntity::class,
            parentColumns = ["id"],
            childColumns = ["pokemonId"]
        ),
        ForeignKey(
            entity = PokemonTypeEntity::class,
            parentColumns = ["id"],
            childColumns = ["typeId"]
        )
    ]
)
class PokemonAndTypeJoin(
    val pokemonId: Int,
    val typeId: Int,
    val isPrimary: Int
)