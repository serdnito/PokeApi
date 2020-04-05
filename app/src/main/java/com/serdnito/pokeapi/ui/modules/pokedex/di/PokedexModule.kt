package com.serdnito.pokeapi.ui.modules.pokedex.di

import com.serdnito.pokeapi.core.di.ActivityScope
import com.serdnito.pokeapi.data.api.PokeApi
import com.serdnito.pokeapi.data.datasource.pokemon.PokemonLocalDataSource
import com.serdnito.pokeapi.data.datasource.pokemon.PokemonRemoteDataSource
import com.serdnito.pokeapi.data.repository.PokemonDataRepository
import com.serdnito.pokeapi.domain.repository.PokemonRepository
import dagger.Module
import dagger.Provides

@Module
class PokedexModule {

    @Provides
    @ActivityScope
    fun providePokemonRepository(
        localDataSource: PokemonLocalDataSource,
        remoteDataSource: PokemonRemoteDataSource
    ): PokemonRepository =
        PokemonDataRepository(localDataSource, remoteDataSource)

}