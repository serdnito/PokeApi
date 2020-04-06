package com.serdnito.pokeapi.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.serdnito.pokeapi.data.database.entity.StatEntity
import io.reactivex.Completable

@Dao
interface StatDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertAll(stats: List<StatEntity>): Completable

}
