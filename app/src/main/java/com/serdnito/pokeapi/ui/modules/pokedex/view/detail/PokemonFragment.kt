package com.serdnito.pokeapi.ui.modules.pokedex.view.detail

import android.annotation.SuppressLint
import android.content.res.ColorStateList
import android.graphics.Color
import android.view.View
import androidx.annotation.ColorInt
import androidx.navigation.fragment.navArgs
import com.serdnito.pokeapi.R
import com.serdnito.pokeapi.core.mvp.BaseFragment
import com.serdnito.pokeapi.domain.model.Pokemon
import com.serdnito.pokeapi.ui.modules.pokedex.di.inject
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_pokemon.*
import javax.inject.Inject
import kotlin.math.roundToInt

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

    override fun injectDependencies() {
        inject()
    }

    @SuppressLint("DefaultLocale")
    override fun showPokemon(pokemon: Pokemon) {
        imgSprite?.let { Picasso.get().load(pokemon.urlFrontSprite).into(it) }
        val bgColor = Color.parseColor(pokemon.types[0].getColor().bgHexColor)
        framePokemon?.setBackgroundColor(bgColor)
        val textColor = Color.parseColor(pokemon.types[0].getColor().textHexColor)
        val formattedNumber = pokemon.id.toString().padStart(3, '0')
        txtNumber?.run {
            setTextColor(textColor)
            text = resources.getString(R.string.pokedex_number, formattedNumber)
        }
        txtName?.run {
            setTextColor(textColor)
            text = pokemon.name.capitalize()
        }
        val chipCsl = getChipCsl(bgColor)
        chipPrimaryType?.run {
            chipBackgroundColor = chipCsl
            setTextColor(textColor)
            text = pokemon.types[0].name.capitalize()
        }
        if (pokemon.types.size > 1) {
            chipSecondaryType?.run {
                chipBackgroundColor = chipCsl
                setTextColor(textColor)
                text = pokemon.types[1].name.capitalize()
                visibility = View.VISIBLE
            }
        } else {
            chipSecondaryType?.visibility = View.INVISIBLE
        }
    }

    private fun getChipCsl(@ColorInt color: Int): ColorStateList {
        val states = arrayOf(
            intArrayOf(android.R.attr.state_checked)
        )
        val halfTransparentColor = Color.argb(
            (Color.alpha(color) * 0.75).roundToInt(),
            Color.red(color),
            Color.green(color),
            Color.blue(color)
        )
        val colors = intArrayOf(halfTransparentColor)
        return ColorStateList(states, colors)
    }

}