package com.serdnito.pokeapi.data.datasource.pokemon

import com.serdnito.pokeapi.core.executor.Executor
import com.serdnito.pokeapi.core.ktx.flatMapCompletable
import com.serdnito.pokeapi.core.ktx.mapSingle
import com.serdnito.pokeapi.data.database.PokeDatabase
import com.serdnito.pokeapi.data.database.entity.*
import com.serdnito.pokeapi.domain.model.*
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single
import io.reactivex.functions.Function3
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
                    getPokemonAbilities(id),
                    getPokemonTypes(id),
                    getPokemonStats(id),
                    Function3<List<PokemonAbilityEntity>, List<TypeEntity>, List<PokemonStatEntity>, Pokemon> { abilities, types, stats ->
                        pokemon.mapToDomain(abilities, types, stats)
                    }
                )
            }
            .toFlowable()

    private fun getPokemonAbilities(pokemonId: Int) =
        database.pokemonJoinAbilityDao().getAbilitiesForPokemon(pokemonId)
            .subscribeOn(executor.io)

    private fun getPokemonStats(pokemonId: Int) =
        database.pokemonJoinStatDao().getStatsForPokemon(pokemonId)
            .subscribeOn(executor.io)

    private fun getPokemonTypes(pokemonId: Int) =
        database.pokemonJoinTypeDao().getTypesForPokemon(pokemonId)
            .subscribeOn(executor.io)

    fun getSpecies(id: Int): Single<Species> =
        database.speciesDao().select(id)
            .map { it.mapToDomain() }

    fun savePokedex(namedResourceList: NamedResourceList): Single<NamedResourceList> =
        namedResourceList.list.map { PokedexItemEntity(it.id, it.name) }
            .flatMapCompletable { database.pokedexDao().insertAll(it) }
            .toSingleDefault(namedResourceList)

    fun savePokemon(pokemon: Pokemon): Single<Pokemon> {
        val dbOperations = listOf(
            savePokemonInfo(pokemon),
            saveAbilities(pokemon.abilities),
            savePokemonAbilities(pokemon),
            saveStats(pokemon.stats),
            savePokemonStats(pokemon),
            saveTypes(pokemon.types),
            savePokemonTypes(pokemon)
        )
        return Completable.concat(dbOperations).toSingleDefault(pokemon)
    }

    private fun savePokemonInfo(pokemon: Pokemon) =
        PokemonEntity.mapFromDomain(pokemon)
            .mapSingle()
            .flatMapCompletable { database.pokemonDao().insert(it) }
            .subscribeOn(executor.io)

    private fun savePokemonAbilities(pokemon: Pokemon) =
        PokemonJoinAbilityEntity.mapFromDomain(pokemon, pokemon.abilities)
            .mapSingle()
            .flatMapCompletable { database.pokemonJoinAbilityDao().insertAll(it) }
            .subscribeOn(executor.io)

    private fun savePokemonStats(pokemon: Pokemon) =
        PokemonJoinStatEntity.mapFromDomain(pokemon, pokemon.stats)
            .mapSingle()
            .flatMapCompletable { database.pokemonJoinStatDao().insertAll(it) }
            .subscribeOn(executor.io)

    private fun savePokemonTypes(pokemon: Pokemon) =
        PokemonJoinTypeEntity.mapFromDomain(pokemon)
            .mapSingle()
            .flatMapCompletable { database.pokemonJoinTypeDao().insertAll(it) }
            .subscribeOn(executor.io)

    private fun saveAbilities(abilities: List<PokemonAbility>) =
        AbilityEntity.mapFromDomain(abilities)
            .mapSingle()
            .flatMapCompletable { database.pokemonAbilityDao().insertAll(it) }
            .subscribeOn(executor.io)

    fun saveSpecies(species: Species) =
        SpeciesEntity.mapFromDomain(species)
            .mapSingle()
            .flatMapCompletable { database.speciesDao().insert(it) }
            .toSingleDefault(species)

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
