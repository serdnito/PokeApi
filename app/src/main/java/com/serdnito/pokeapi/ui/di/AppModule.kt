package com.serdnito.pokeapi.ui.di

import android.content.Context
import com.serdnito.pokeapi.core.error.AndroidErrorHandler
import com.serdnito.pokeapi.core.error.ErrorHandler
import com.serdnito.pokeapi.core.executor.AndroidExecutor
import com.serdnito.pokeapi.core.executor.Executor
import com.serdnito.pokeapi.ui.App
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule(
    private val application: App
) {

    @Provides
    @Singleton
    fun provideContext(): Context = application

    @Provides
    @Singleton
    fun provideErrorHandler(): ErrorHandler = AndroidErrorHandler(application)

    @Provides
    @Singleton
    fun provideExecutor(): Executor = AndroidExecutor()

}