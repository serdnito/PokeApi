package com.serdnito.pokeapi.ui.di

import android.content.Context
import com.serdnito.pokeapi.data.database.PokeDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DatabaseModule(
    private val appContext: Context
) {

    @Provides
    @Singleton
    fun providePokeDatabase(): PokeDatabase =
        PokeDatabase.getInstance(appContext)

}