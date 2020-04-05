package com.serdnito.pokeapi.ui.modules.pokedex.view

import android.os.Bundle
import com.serdnito.pokeapi.R
import com.serdnito.pokeapi.core.mvp.BaseActivity
import com.serdnito.pokeapi.ui.App
import com.serdnito.pokeapi.ui.modules.pokedex.di.DaggerPokedexComponent
import com.serdnito.pokeapi.ui.modules.pokedex.di.PokedexModule

class PokedexActivity : BaseActivity(R.navigation.pokedex) {

    val pokedexComponent by lazy {
        val appComponent = (application as App).appComponent
        val pokedexModule = PokedexModule()
        DaggerPokedexComponent.builder()
            .appComponent(appComponent)
            .pokedexModule(pokedexModule)
            .build()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        pokedexComponent.inject(this)
        super.onCreate(savedInstanceState)
    }

}