package com.serdnito.pokeapi.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.serdnito.pokeapi.data.database.entity.PokemonAndTypeJoin
import com.serdnito.pokeapi.data.database.entity.PokemonTypeEntity
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single

@Dao
interface PokemonAndTypeJoinDao {

    @Insert
    fun insertAll(pokemonAndTypeJoin: List<PokemonAndTypeJoin>): Completable

    @Query(
        """
        SELECT * FROM pokemon_type
        INNER JOIN pokemon_type_join
        ON pokemon_type_join.typeId = pokemon_type.id
        WHERE pokemon_type_join.pokemonId = :pokemonId
        ORDER by pokemon_type_join.isPrimary ASC
        """
    )
    fun getTypesForPokemon(pokemonId: Int): Single<List<PokemonTypeEntity>>

}
