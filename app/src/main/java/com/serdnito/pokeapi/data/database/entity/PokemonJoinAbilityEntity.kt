package com.serdnito.pokeapi.data.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import com.serdnito.pokeapi.domain.model.Pokemon
import com.serdnito.pokeapi.domain.model.PokemonAbility

@Entity(
    tableName = "pokemon_ability_join",
    primaryKeys = ["pokemon_id", "ability_id"],
    foreignKeys = [
        ForeignKey(
            entity = PokemonEntity::class,
            parentColumns = ["id"],
            childColumns = ["pokemon_id"]
        ),
        ForeignKey(
            entity = AbilityEntity::class,
            parentColumns = ["id"],
            childColumns = ["ability_id"]
        )
    ]
)
class PokemonJoinAbilityEntity(
    @ColumnInfo(name = "pokemon_id") val pokemonId: Int,
    @ColumnInfo(name = "ability_id") val abilityId: Int,
    @ColumnInfo(name = "is_hidden") val isHidden: Boolean
) {

    companion object {
        fun mapFromDomain(pokemon: Pokemon, abilities: List<PokemonAbility>) =
            abilities.map { pokemonAbility ->
                PokemonJoinAbilityEntity(
                    pokemon.id,
                    pokemonAbility.id,
                    pokemonAbility.isHidden
                )
            }
    }

}