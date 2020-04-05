package com.serdnito.pokeapi.core.mvp

interface BaseView {
    fun hideLoading()
    fun showError(message: String, retryAction: () -> Unit)
    fun showLoading()
    fun showMessage(message: String?)
}