package com.serdnito.pokeapi.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.serdnito.pokeapi.data.database.entity.SpeciesEntity
import io.reactivex.Completable
import io.reactivex.Single

@Dao
interface SpeciesDao {

    @Query("SELECT * FROM species WHERE id = :id")
    fun select(id: Int): Single<SpeciesEntity>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(species: SpeciesEntity): Completable

}
