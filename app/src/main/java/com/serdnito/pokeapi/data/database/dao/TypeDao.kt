package com.serdnito.pokeapi.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.serdnito.pokeapi.data.database.entity.TypeEntity
import io.reactivex.Completable

@Dao
interface TypeDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertAll(types: List<TypeEntity>): Completable

}
