package com.serdnito.pokeapi.ui.modules.pokedex.view.list

import com.serdnito.pokeapi.core.error.ErrorHandler
import com.serdnito.pokeapi.core.ktx.subscribeBy
import com.serdnito.pokeapi.core.mvp.Presenter
import com.serdnito.pokeapi.core.mvp.BaseView
import com.serdnito.pokeapi.domain.model.Pokemon
import com.serdnito.pokeapi.domain.usecase.GetPokedex
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class PokedexPresenter @Inject constructor(
    private val errorHandler: ErrorHandler,
    private val getPokedex: GetPokedex
) : Presenter<PokedexPresenter.PokedexView>() {

    private val compositeDisposable = CompositeDisposable()
    private var isLoading = false

    fun start(pokedex: MutableList<Pokemon>) {
        if (pokedex.isEmpty()) {
            getNewPokemon(pokedex)
        }
    }

    fun stop() {
        compositeDisposable.clear()
    }

    private fun getNewPokemon(pokedex: MutableList<Pokemon>) {
        isLoading = true
        view?.showLoading()
        val offset = pokedex.size
        val input = GetPokedex.Input(offset)
        getPokedex.invoke(input)
            .doOnSubscribe { compositeDisposable.add(it) }
            .subscribeBy(
                onError = {
                    val message = errorHandler.convert(it)
                    view?.run {
                        hideLoading()
                        showError(message) { start(pokedex) }
                    }
                    isLoading = false
                },
                onSuccess = {
                    pokedex.addAll(it.pokedex.pokemon)
                    view?.run {
                        hideLoading()
                        updatePokedex(offset, it.pokedex.pokemon.size)
                    }
                    isLoading = false
                })
    }

    fun onPokedexReachBottom(pokedex: MutableList<Pokemon>) {
        if (!isLoading) {
            getNewPokemon(pokedex)
        }
    }

    fun onPokemonClick(pokemon: Pokemon) {
        view?.openPokemonDetail(pokemon.id)
    }

    interface PokedexView : BaseView {
        fun openPokemonDetail(pokemonId: Int)
        fun updatePokedex(from: Int, count: Int)
    }

}