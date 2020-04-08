package com.serdnito.pokeapi.data.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import com.serdnito.pokeapi.domain.model.Pokemon
import com.serdnito.pokeapi.domain.model.PokemonStat

@Entity(
    tableName = "pokemon_stat_join",
    primaryKeys = ["pokemon_id", "stat_id"],
    foreignKeys = [
        ForeignKey(
            entity = PokemonEntity::class,
            parentColumns = ["id"],
            childColumns = ["pokemon_id"]
        ),
        ForeignKey(
            entity = StatEntity::class,
            parentColumns = ["id"],
            childColumns = ["stat_id"]
        )
    ]
)
class PokemonJoinStatEntity(
    @ColumnInfo(name = "pokemon_id") val pokemonId: Int,
    @ColumnInfo(name = "stat_id") val statId: Int,
    @ColumnInfo(name = "max") val max: Int,
    @ColumnInfo(name = "value") val value: Int
) {

    companion object {
        fun mapFromDomain(pokemon: Pokemon, stats: List<PokemonStat>) =
            stats.map { pokemonStat ->
                PokemonJoinStatEntity(
                    pokemon.id,
                    pokemonStat.stat.id,
                    pokemonStat.max,
                    pokemonStat.value
                )
            }
    }

}