package com.serdnito.pokeapi.core.mvp

abstract class Presenter<T : BaseView> {

    var view: T? = null

}