package com.serdnito.pokeapi.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.serdnito.pokeapi.data.database.entity.PokemonAndStatJoin
import com.serdnito.pokeapi.data.database.entity.PokemonStatEntity
import io.reactivex.Completable
import io.reactivex.Single

@Dao
interface PokemonAndStatJoinDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertAll(pokemonAndStatJoin: List<PokemonAndStatJoin>): Completable

    @Query(
        """
        SELECT pokemon_stat_join.stat_id, pokemon_stat_join.stat_value, stats.name
        FROM stats
        INNER JOIN pokemon_stat_join
        ON pokemon_stat_join.stat_id = stats.id
        WHERE pokemon_stat_join.pokemon_id = :pokemonId
        """
    )
    fun getStatsForPokemon(pokemonId: Int): Single<List<PokemonStatEntity>>

}
