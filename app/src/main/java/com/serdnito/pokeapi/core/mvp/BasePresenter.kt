package com.serdnito.pokeapi.core.mvp

abstract class BasePresenter<T : BaseView> {

    var view: T? = null

}