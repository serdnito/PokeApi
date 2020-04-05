package com.serdnito.pokeapi.ui

import android.app.Application
import androidx.appcompat.app.AppCompatDelegate
import androidx.preference.PreferenceManager
import com.serdnito.pokeapi.ui.di.AppComponent
import com.serdnito.pokeapi.ui.di.AppModule
import com.serdnito.pokeapi.ui.di.DaggerAppComponent
import com.serdnito.pokeapi.ui.di.DatabaseModule

class App : Application() {

    val appComponent: AppComponent by lazy {
        val appModule = AppModule(this)
        val databaseModule = DatabaseModule(this)
        DaggerAppComponent
            .builder()
            .appModule(appModule)
            .databaseModule(databaseModule)
            .build()
    }

    override fun onCreate() {
        appComponent.inject(this)
        super.onCreate()
        PreferenceManager.getDefaultSharedPreferences(this)
            .getString("theme", AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM.toString())
            ?.toInt()
            ?.let { AppCompatDelegate.setDefaultNightMode(it) }
    }

}