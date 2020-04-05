package com.serdnito.pokeapi.ui.modules.home

import androidx.navigation.fragment.findNavController
import com.serdnito.pokeapi.R
import com.serdnito.pokeapi.core.mvp.BaseFragment
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : BaseFragment(R.layout.fragment_home) {

    override fun initPresenter() {
        // TODO
    }

    override fun initUi() {
        // TODO
        btnPokedex?.setOnClickListener {
            val action = HomeFragmentDirections.actionHomeToPokedex()
            findNavController().navigate(action)
        }
        btnSettings?.setOnClickListener {
            val action = HomeFragmentDirections.actionHomeToSettings()
            findNavController().navigate(action)
        }
    }

    override fun injectDependencies() {
        // TODO
    }

}