package com.serdnito.pokeapi.ui.modules.pokedex.view.list

import android.annotation.SuppressLint
import android.graphics.Color
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.serdnito.pokeapi.R
import com.serdnito.pokeapi.core.ktx.inflate
import com.serdnito.pokeapi.core.ktx.setCheckedBgColor
import com.serdnito.pokeapi.core.ktx.setTextColor
import com.serdnito.pokeapi.domain.model.Pokemon
import com.serdnito.pokeapi.domain.model.PokemonType
import com.squareup.picasso.Picasso
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.view_holder_pokedex.*

@SuppressLint("DefaultLocale")
class PokedexAdapter(
    private val pokedex: MutableList<Pokemon>,
    private val onPokemonClick: (Pokemon) -> Unit
) : RecyclerView.Adapter<PokedexAdapter.PokemonViewHolder>() {

    override fun getItemCount() = pokedex.size

    override fun onBindViewHolder(viewHolder: PokemonViewHolder, position: Int) {
        viewHolder.bind(pokedex[position])
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonViewHolder {
        val view = parent.inflate(R.layout.view_holder_pokedex)
        val viewHolder = PokemonViewHolder(view)
        view.setOnClickListener {
            if (viewHolder.adapterPosition != RecyclerView.NO_POSITION) {
                val pokemon = pokedex[viewHolder.adapterPosition]
                onPokemonClick(pokemon)
            }
        }
        return viewHolder
    }

    class PokemonViewHolder(view: View) : RecyclerView.ViewHolder(view), LayoutContainer {

        override val containerView = itemView

        fun bind(pokemon: Pokemon) {
            imgSprite?.let { Picasso.get().load(pokemon.urlFrontSprite).into(it) }
            val bgColor = Color.parseColor(pokemon.types[0].getColor().bgHexColor)
            cardPokemon?.setCardBackgroundColor(bgColor)
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
            bindTypes(pokemon.types)
        }

        private fun bindTypes(types: List<PokemonType>) {
            chipPrimaryType?.run {
                setCheckedBgColor(types[0].getColor().bgHexColor)
                setTextColor(types[0].getColor().textHexColor)
                text = types[0].name.capitalize()
            }
            if (types.size > 1) {
                chipSecondaryType?.run {
                    setCheckedBgColor(types[1].getColor().bgHexColor)
                    setTextColor(types[1].getColor().textHexColor)
                    text = types[1].name.capitalize()
                    visibility = View.VISIBLE
                }
            } else {
                chipSecondaryType?.visibility = View.INVISIBLE
            }
        }

    }

}