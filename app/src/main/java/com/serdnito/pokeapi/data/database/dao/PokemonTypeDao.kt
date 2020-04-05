package com.serdnito.pokeapi.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.serdnito.pokeapi.data.database.entity.PokedexItemEntity
import com.serdnito.pokeapi.data.database.entity.PokemonEntity
import com.serdnito.pokeapi.data.database.entity.PokemonTypeEntity
import io.reactivex.Completable
import io.reactivex.Single

@Dao
interface PokemonTypeDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertAll(pokemonTypes: List<PokemonTypeEntity>): Completable

}
