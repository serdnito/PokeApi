package com.serdnito.pokeapi.data.repository

import com.serdnito.pokeapi.data.datasource.pokemon.PokemonLocalDataSource
import com.serdnito.pokeapi.data.datasource.pokemon.PokemonRemoteDataSource
import com.serdnito.pokeapi.domain.model.NamedResourceList
import com.serdnito.pokeapi.domain.model.Pokemon
import com.serdnito.pokeapi.domain.model.Species
import com.serdnito.pokeapi.domain.repository.PokemonRepository
import io.reactivex.Flowable
import io.reactivex.Single
import javax.inject.Inject

class PokemonDataRepository @Inject constructor(
    private val localDataSource: PokemonLocalDataSource,
    private val remoteDataSource: PokemonRemoteDataSource
) : PokemonRepository {

    override fun getPokedex(offset: Int, limit: Int): Single<NamedResourceList> =
        localDataSource.getPokedex(offset)
            .map { localList ->
                if (localList.isEmpty()) {
                    remoteDataSource.getPokedex(offset, limit)
                        .flatMap { localDataSource.savePokedex(it) }
                        .blockingGet()
                } else {
                    localList
                }
            }

    override fun getPokemon(id: Int): Flowable<Pokemon> =
        localDataSource.getPokemon(id)
            .onErrorResumeNext { _: Throwable ->
                remoteDataSource.getPokemon(id)
                    .flatMapSingle { localDataSource.savePokemon(it) }
            }

    override fun getSpecies(id: Int): Single<Species> =
        localDataSource.getSpecies(id)
            .onErrorResumeNext {
                remoteDataSource.getSpecies(id)
                    .flatMap { localDataSource.saveSpecies(it) }
            }

}