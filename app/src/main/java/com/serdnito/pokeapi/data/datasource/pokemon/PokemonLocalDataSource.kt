package com.serdnito.pokeapi.data.datasource.pokemon

import com.serdnito.pokeapi.core.ktx.flatMapCompletable
import com.serdnito.pokeapi.core.ktx.mapSingle
import com.serdnito.pokeapi.core.executor.Executor
import com.serdnito.pokeapi.data.database.PokeDatabase
import com.serdnito.pokeapi.data.database.entity.*
import com.serdnito.pokeapi.domain.model.NamedResourceList
import com.serdnito.pokeapi.domain.model.Pokemon
import com.serdnito.pokeapi.domain.model.PokemonType
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single
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
                database.pokemonAndTypeJoinDao().getTypesForPokemon(id)
                    .map { pokemon.mapToDomain(it) }
            }
            .toFlowable()

    fun savePokedex(namedResourceList: NamedResourceList): Single<NamedResourceList> =
        namedResourceList.list.map { PokedexItemEntity(it.id, it.name) }
            .flatMapCompletable { database.pokedexDao().insertAll(it) }
            .toSingleDefault(namedResourceList)

    fun savePokemon(pokemon: Pokemon): Single<Pokemon> {
        val dbOperations = listOf(
            pokemon.mapToEntity().mapSingle()
                .flatMapCompletable { database.pokemonDao().insert(it) }
                .subscribeOn(executor.io),
            pokemon.types.mapToEntity().mapSingle()
                .flatMapCompletable { database.pokemonTypeDao().insertAll(it) }
                .subscribeOn(executor.io),
            pokemon.types.mapToEntity(pokemon).mapSingle()
                .flatMapCompletable { database.pokemonAndTypeJoinDao().insertAll(it) }
                .subscribeOn(executor.io)
        )
        return Completable.concat(dbOperations).toSingleDefault(pokemon)
    }

    private fun Pokemon.mapToEntity() =
        PokemonEntity(id, name, order, urlFrontSprite)

    private fun List<PokemonType>.mapToEntity() =
        map { PokemonTypeEntity(it.id, it.name) }

    private fun List<PokemonType>.mapToEntity(pokemon: Pokemon) =
        mapIndexed { index, pokemonType ->
            PokemonAndTypeJoin(
                pokemon.id,
                pokemonType.id,
                if (size == 1 || (size > 1 && index != 0)) 1 else 0
            )
        }

}
