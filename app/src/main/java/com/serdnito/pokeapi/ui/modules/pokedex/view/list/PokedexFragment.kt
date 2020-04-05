package com.serdnito.pokeapi.ui.modules.pokedex.view.list

import android.os.Bundle
import android.view.*
import androidx.navigation.fragment.findNavController
import com.serdnito.pokeapi.R
import com.serdnito.pokeapi.core.ktx.addOnBottomReachListener
import com.serdnito.pokeapi.core.ktx.attachTo
import com.serdnito.pokeapi.core.mvp.BaseFragment
import com.serdnito.pokeapi.domain.model.Pokemon
import com.serdnito.pokeapi.ui.modules.pokedex.di.inject
import kotlinx.android.synthetic.main.fragment_pokedex.*
import javax.inject.Inject

class PokedexFragment : BaseFragment(R.layout.fragment_pokedex), PokedexPresenter.PokedexView {

    @Inject
    lateinit var presenter: PokedexPresenter

    private val pokedex = mutableListOf<Pokemon>()
    private val adapterPokemon = PokemonAdapter(pokedex) { presenter.onPokemonClick(it) }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_main, menu)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setHasOptionsMenu(true)
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onDestroyView() {
        presenter.stop()
        super.onDestroyView()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.menuSettings) {
            presenter.onSettingsClick()
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    override fun initPresenter() {
        presenter.view = this
        presenter.start(pokedex)
    }

    override fun initUi() {
        recyclerView?.run {
            addOnBottomReachListener { presenter.onBottomReach(pokedex) }
            setHasFixedSize(true)
            PokedexDecoration(resources).attachTo(this)
            adapter = adapterPokemon
        }
    }

    override fun injectDependencies() {
        inject()
    }

    override fun openPokemonDetail(pokemonId: Int) {
        val action = PokedexFragmentDirections.actionPokedexToPokemon(pokemonId)
        findNavController().navigate(action)
    }

    override fun openSettings() {
        val action = PokedexFragmentDirections.actionPokedexToSettings()
        findNavController().navigate(action)
    }

    override fun updatePokedex(from: Int, count: Int) {
        adapterPokemon.notifyItemRangeInserted(from, count)
    }

}
