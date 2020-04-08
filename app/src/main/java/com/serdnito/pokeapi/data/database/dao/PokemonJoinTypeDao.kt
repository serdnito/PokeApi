package com.serdnito.pokeapi.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.serdnito.pokeapi.data.database.entity.PokemonJoinTypeEntity
import com.serdnito.pokeapi.data.database.entity.TypeEntity
import io.reactivex.Completable
import io.reactivex.Single

@Dao
interface PokemonJoinTypeDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertAll(pokemonAndTypeJoin: List<PokemonJoinTypeEntity>): Completable

    @Query(
        """
        SELECT * FROM types
        INNER JOIN pokemon_type_join
        ON pokemon_type_join.type_id = types.id
        WHERE pokemon_type_join.pokemon_id = :pokemonId
        ORDER by pokemon_type_join.is_primary ASC
        """
    )
    fun getTypesForPokemon(pokemonId: Int): Single<List<TypeEntity>>

}
