package com.serdnito.pokeapi.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.serdnito.pokeapi.data.database.dao.*
import com.serdnito.pokeapi.data.database.entity.*

@Database(
    entities = [
        PokedexItemEntity::class,
        PokemonEntity::class,
        StatEntity::class,
        PokemonAndStatJoin::class,
        TypeEntity::class,
        PokemonAndTypeJoin::class
    ],
    version = 1
)
abstract class PokeDatabase : RoomDatabase() {

    companion object {

        @Volatile
        private var instance: PokeDatabase? = null
        private const val DATABASE_NAME = "PokeDatabase.db"

        fun getInstance(context: Context) =
            instance ?: synchronized(this) {
                instance ?: buildDatabase(context).also { instance = it }
            }

        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(context, PokeDatabase::class.java, DATABASE_NAME).build()

    }

    abstract fun pokedexDao(): PokedexDao
    abstract fun pokemonDao(): PokemonDao
    abstract fun pokemonStatDao(): StatDao
    abstract fun pokemonAndStatJoinDao(): PokemonAndStatJoinDao
    abstract fun pokemonTypeDao(): TypeDao
    abstract fun pokemonAndTypeJoinDao(): PokemonAndTypeJoinDao

}