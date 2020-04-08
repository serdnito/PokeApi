package com.serdnito.pokeapi.ui.modules.pokedex.view.detail

import com.serdnito.pokeapi.core.error.ErrorHandler
import com.serdnito.pokeapi.core.ktx.subscribeBy
import com.serdnito.pokeapi.core.mvp.BaseView
import com.serdnito.pokeapi.core.mvp.Presenter
import com.serdnito.pokeapi.domain.model.Pokemon
import com.serdnito.pokeapi.domain.model.Species
import com.serdnito.pokeapi.domain.usecase.GetPokemon
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class PokemonPresenter @Inject constructor(
    private val errorHandler: ErrorHandler,
    private val getPokemon: GetPokemon
) : Presenter<PokemonPresenter.PokemonView>() {

    private val compositeDisposable = CompositeDisposable()

    fun start(pokemonId: Int) {
        view?.showLoading()
        val input = GetPokemon.Input(pokemonId)
        getPokemon.invoke(input)
            .doOnSubscribe { compositeDisposable.add(it) }
            .subscribeBy(
                onError = {
                    val message = errorHandler.convert(it)
                    view?.run {
                        hideLoading()
                        showError(message) { start(pokemonId) }
                    }
                },
                onSuccess = {
                    view?.run {
                        hideLoading()
                        showPokemon(it.pokemon, it.species)
                    }
                })
    }

    fun stop() {
        compositeDisposable.clear()
    }

    fun onCloseClick() {
        view?.close()
    }

    interface PokemonView : BaseView {
        fun close()
        fun showPokemon(pokemon: Pokemon, species: Species)
    }

}