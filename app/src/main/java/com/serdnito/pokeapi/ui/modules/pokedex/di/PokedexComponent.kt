package com.serdnito.pokeapi.ui.modules.pokedex.di

import com.serdnito.pokeapi.core.di.ActivityScope
import com.serdnito.pokeapi.ui.di.AppComponent
import com.serdnito.pokeapi.ui.modules.pokedex.view.PokedexActivity
import com.serdnito.pokeapi.ui.modules.pokedex.view.detail.PokemonFragment
import com.serdnito.pokeapi.ui.modules.pokedex.view.list.PokedexFragment
import dagger.Component

@ActivityScope
@Component(
    dependencies = [AppComponent::class],
    modules = [PokedexModule::class]
)
interface PokedexComponent {
    fun inject(activity: PokedexActivity)
    fun inject(fragment: PokedexFragment)
    fun inject(fragment: PokemonFragment)
}