package com.serdnito.pokeapi.data.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.serdnito.pokeapi.domain.model.PokemonAbility

@Entity(
    tableName = "abilities"
)
class AbilityEntity(
    @PrimaryKey val id: Int,
    @ColumnInfo val name: String
) {

    companion object {
        fun mapFromDomain(abilities: List<PokemonAbility>) =
            abilities.map { AbilityEntity(it.id, it.name) }
    }

}