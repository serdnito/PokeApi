package com.serdnito.pokeapi.domain.usecase

import com.serdnito.pokeapi.core.executor.Executor
import com.serdnito.pokeapi.core.usecase.RxSingleUseCase
import com.serdnito.pokeapi.domain.model.Pokemon
import com.serdnito.pokeapi.domain.repository.PokemonRepository
import javax.inject.Inject

class GetPokemon @Inject constructor(
    executor: Executor,
    private val pokemonRepository: PokemonRepository
) : RxSingleUseCase<GetPokemon.Input, GetPokemon.Output>(executor) {

    override fun build(input: Input) =
        pokemonRepository.getPokemon(input.pokemonId)
            .singleOrError()
            .map { Output(it) }

    class Input(
        val pokemonId: Int
    )

    class Output(
        val pokemon: Pokemon
    )

}