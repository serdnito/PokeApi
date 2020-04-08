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
        AbilityEntity::class,
        PokemonJoinAbilityEntity::class,
        StatEntity::class,
        PokemonJoinStatEntity::class,
        TypeEntity::class,
        PokemonJoinTypeEntity::class
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
    abstract fun pokemonAbilityDao(): AbilityDao
    abstract fun pokemonJoinAbilityDao(): PokemonJoinAbilityDao
    abstract fun pokemonStatDao(): StatDao
    abstract fun pokemonJoinStatDao(): PokemonJoinStatDao
    abstract fun pokemonTypeDao(): TypeDao
    abstract fun pokemonJoinTypeDao(): PokemonJoinTypeDao

}