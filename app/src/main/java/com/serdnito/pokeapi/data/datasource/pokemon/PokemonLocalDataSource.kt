package com.serdnito.pokeapi.data.datasource.pokemon

import com.serdnito.pokeapi.core.executor.Executor
import com.serdnito.pokeapi.core.ktx.flatMapCompletable
import com.serdnito.pokeapi.core.ktx.mapSingle
import com.serdnito.pokeapi.data.database.PokeDatabase
import com.serdnito.pokeapi.data.database.entity.*
import com.serdnito.pokeapi.domain.model.NamedResourceList
import com.serdnito.pokeapi.domain.model.Pokemon
import com.serdnito.pokeapi.domain.model.PokemonStat
import com.serdnito.pokeapi.domain.model.PokemonType
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single
import io.reactivex.functions.BiFunction
import javax.inject.Inject

class PokemonLocalDataSource @Inject constructor(
    private val database: PokeDatabase,
    private val executor: Executor
) {

    fun getPokedex(offset: Int): Single<NamedResourceList> =
        database.pokedexDao().select(offset)
            .map { it.mapToDomain() }

    fun getPokemon(id: Int): Flowable<Pokemon> =
        database.pokemonDao().select(id)
            .flatMap { pokemon ->
                Single.zip(
                    getPokemonTypes(id),
                    getPokemonStats(id),
                    BiFunction<List<TypeEntity>, List<PokemonStatEntity>, Pokemon> { types, stats ->
                        pokemon.mapToDomain(types, stats)
                    }
                )
            }
            .toFlowable()

    private fun getPokemonStats(pokemonId: Int) =
        database.pokemonAndStatJoinDao().getStatsForPokemon(pokemonId)
            .subscribeOn(executor.io)

    private fun getPokemonTypes(pokemonId: Int) =
        database.pokemonAndTypeJoinDao().getTypesForPokemon(pokemonId)
            .subscribeOn(executor.io)

    fun savePokedex(namedResourceList: NamedResourceList): Single<NamedResourceList> =
        namedResourceList.list.map { PokedexItemEntity(it.id, it.name) }
            .flatMapCompletable { database.pokedexDao().insertAll(it) }
            .toSingleDefault(namedResourceList)

    fun savePokemon(pokemon: Pokemon): Single<Pokemon> {
        val dbOperations = listOf(
            savePokemonInfo(pokemon),
            saveTypes(pokemon.types),
            savePokemonTypes(pokemon),
            saveStats(pokemon.stats),
            savePokemonStats(pokemon)
        )
        return Completable.concat(dbOperations).toSingleDefault(pokemon)
    }

    private fun savePokemonInfo(pokemon: Pokemon) =
        PokemonEntity.mapFromDomain(pokemon)
            .mapSingle()
            .flatMapCompletable { database.pokemonDao().insert(it) }
            .subscribeOn(executor.io)

    private fun savePokemonStats(pokemon: Pokemon) =
        PokemonAndStatJoin.mapFromDomain(pokemon, pokemon.stats)
            .mapSingle()
            .flatMapCompletable { database.pokemonAndStatJoinDao().insertAll(it) }
            .subscribeOn(executor.io)

    private fun savePokemonTypes(pokemon: Pokemon) =
        PokemonAndTypeJoin.mapFromDomain(pokemon)
            .mapSingle()
            .flatMapCompletable { database.pokemonAndTypeJoinDao().insertAll(it) }
            .subscribeOn(executor.io)

    private fun saveStats(stats: List<PokemonStat>) =
        StatEntity.mapFromDomain(stats)
            .mapSingle()
            .flatMapCompletable { database.pokemonStatDao().insertAll(it) }
            .subscribeOn(executor.io)

    private fun saveTypes(types: List<PokemonType>) =
        TypeEntity.mapFromDomain(types)
            .mapSingle()
            .flatMapCompletable { database.pokemonTypeDao().insertAll(it) }
            .subscribeOn(executor.io)


}
