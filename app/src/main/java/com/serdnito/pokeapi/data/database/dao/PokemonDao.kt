package com.serdnito.pokeapi.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.serdnito.pokeapi.data.database.entity.PokemonEntity
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single

@Dao
interface PokemonDao {

    @Query("SELECT * FROM pokemon WHERE id = :id")
    fun select(id: Int): Single<PokemonEntity>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(pokemon: PokemonEntity): Completable

}
