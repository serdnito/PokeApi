package com.serdnito.pokeapi.ui.modules.pokedex.view.detail

import android.annotation.SuppressLint
import android.graphics.Color
import android.view.View
import androidx.navigation.fragment.navArgs
import com.google.android.material.tabs.TabLayoutMediator
import com.serdnito.pokeapi.R
import com.serdnito.pokeapi.core.ktx.setCheckedBgColor
import com.serdnito.pokeapi.core.ktx.setTextColor
import com.serdnito.pokeapi.core.ktx.tint
import com.serdnito.pokeapi.core.mvp.BaseFragment
import com.serdnito.pokeapi.domain.model.Pokemon
import com.serdnito.pokeapi.ui.modules.pokedex.di.inject
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_pokemon.*
import kotlinx.android.synthetic.main.frame_pokemon_header.*
import javax.inject.Inject

class PokemonFragment : BaseFragment(R.layout.fragment_pokemon), PokemonPresenter.PokemonView {

    @Inject
    lateinit var presenter: PokemonPresenter

    private val args: PokemonFragmentArgs by navArgs()

    override fun onDestroyView() {
        presenter.stop()
        super.onDestroyView()
    }

    override fun close() {
        activity?.onBackPressed()
    }

    override fun initPresenter() {
        presenter.view = this
        presenter.start(args.pokemonId)
    }

    override fun initUi() {
        imgClose?.setOnClickListener { presenter.onCloseClick() }
    }

    @SuppressLint("DefaultLocale")
    private fun initUiHeader(pokemon: Pokemon) {
        imgSprite?.let { Picasso.get().load(pokemon.urlFrontSprite).into(it) }
        val bgColor = Color.parseColor(pokemon.types[0].getColor().bgHexColor)
        framePokemon?.setBackgroundColor(bgColor)
        val textColor = Color.parseColor(pokemon.types[0].getColor().textHexColor)
        txtNumber?.run {
            setTextColor(textColor)
            val formattedNumber = pokemon.id.toString().padStart(3, '0')
            text = resources.getString(R.string.pokedex_number, formattedNumber)
        }
        txtName?.run {
            setTextColor(textColor)
            text = pokemon.name.capitalize()
        }
        chipPrimaryType?.run {
            setCheckedBgColor(pokemon.types[0].getColor().bgHexColor)
            setTextColor(textColor)
            text = pokemon.types[0].name.capitalize()
        }
        if (pokemon.types.size > 1) {
            chipSecondaryType?.run {
                setCheckedBgColor(pokemon.types[1].getColor().bgHexColor)
                setTextColor(pokemon.types[1].getColor().textHexColor)
                text = pokemon.types[1].name.capitalize()
                visibility = View.VISIBLE
            }
        } else {
            chipSecondaryType?.visibility = View.INVISIBLE
        }
        imgClose?.tint(textColor)
    }

    private fun initUiPager(pokemon: Pokemon){
        viewPager?.adapter = PokemonAdapter(pokemon)
        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            val tabTextResId = when (position) {
                0 -> R.string.pokemon_about
                1 -> R.string.pokemon_stats
                2 -> R.string.pokemon_evolution
                else -> R.string.pokemon_moves
            }
            tab.setText(tabTextResId)
        }.attach()
    }

    override fun injectDependencies() {
        inject()
    }

    override fun showPokemon(pokemon: Pokemon) {
        initUiHeader(pokemon)
        initUiPager(pokemon)
        framePokemon?.visibility = View.VISIBLE
    }

}