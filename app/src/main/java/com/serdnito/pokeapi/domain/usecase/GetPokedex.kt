package com.serdnito.pokeapi.domain.usecase

import com.serdnito.pokeapi.core.executor.Executor
import com.serdnito.pokeapi.core.usecase.RxSingleUseCase
import com.serdnito.pokeapi.domain.model.Pokedex
import com.serdnito.pokeapi.domain.repository.PokemonRepository
import io.reactivex.Flowable
import javax.inject.Inject

class GetPokedex @Inject constructor(
    private val executor: Executor,
    private val pokemonRepository: PokemonRepository
) : RxSingleUseCase<GetPokedex.Input, GetPokedex.Output>(executor) {

    override fun build(input: Input) =
        pokemonRepository.getPokedex(input.offset, 10)
            .flatMap { namedResList ->
                Flowable.fromIterable(namedResList.list)
                    .parallel(namedResList.list.size)
                    .runOn(executor.io)
                    .flatMap { namedRes -> pokemonRepository.getPokemon(namedRes.id) }
                    .sequentialDelayError()
                    .toList()
            }
            .map {
                val pokedex = Pokedex(it.sortedBy { pokemon -> pokemon.order })
                Output(pokedex)
            }

    class Input(
        val offset: Int
    )

    class Output(
        val pokedex: Pokedex
    )

}