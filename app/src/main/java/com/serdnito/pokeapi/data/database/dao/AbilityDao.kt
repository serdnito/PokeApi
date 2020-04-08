package com.serdnito.pokeapi.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.serdnito.pokeapi.data.database.entity.AbilityEntity
import io.reactivex.Completable

@Dao
interface AbilityDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertAll(types: List<AbilityEntity>): Completable

}
