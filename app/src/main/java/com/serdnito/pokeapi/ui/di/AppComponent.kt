package com.serdnito.pokeapi.ui.di

import android.content.Context
import com.serdnito.pokeapi.core.error.ErrorHandler
import com.serdnito.pokeapi.core.executor.Executor
import com.serdnito.pokeapi.data.api.PokeApi
import com.serdnito.pokeapi.data.database.PokeDatabase
import com.serdnito.pokeapi.ui.App
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [AppModule::class, NetworkModule::class, DatabaseModule::class]
)
interface AppComponent {
    fun api(): PokeApi
    fun context(): Context
    fun database(): PokeDatabase
    fun errorHandler(): ErrorHandler
    fun executor(): Executor

    fun inject(app: App)
}