package com.serdnito.pokeapi.core.mvp

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment

abstract class BaseFragment(@LayoutRes layoutId: Int) : Fragment(layoutId), BaseView {

    override fun onAttach(context: Context) {
        super.onAttach(context)
        injectDependencies()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initUi()
        initPresenter()
    }

    override fun hideLoading() {
        (activity as BaseActivity).hideLoading()
    }

    abstract fun initPresenter()

    abstract fun initUi()

    abstract fun injectDependencies()

    override fun showError(message: String, retryAction: () -> Unit) {
        (activity as BaseActivity).showError(message, retryAction)
    }

    override fun showLoading() {
        (activity as BaseActivity).showLoading()
    }

    override fun showMessage(message: String?) {
        (activity as BaseActivity).showMessage(message)
    }

}