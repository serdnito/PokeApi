package com.serdnito.pokeapi.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.serdnito.pokeapi.data.database.entity.PokedexItemEntity
import io.reactivex.Completable
import io.reactivex.Single

@Dao
interface PokedexDao {

    @Query("SELECT * FROM pokedex WHERE pokedex.id >= :offset + 1 AND pokedex.id <= :offset + 20")
    fun select(offset: Int): Single<List<PokedexItemEntity>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertAll(list: List<PokedexItemEntity>): Completable

}
