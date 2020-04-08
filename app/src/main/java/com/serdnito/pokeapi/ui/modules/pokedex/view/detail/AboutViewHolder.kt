package com.serdnito.pokeapi.ui.modules.pokedex.view.detail

import android.annotation.SuppressLint
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.serdnito.pokeapi.R
import com.serdnito.pokeapi.core.ktx.inflate
import com.serdnito.pokeapi.domain.model.Pokemon
import com.serdnito.pokeapi.domain.model.Species
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.view_holder_pokemon_about.*

class AboutViewHolder(view: View) : RecyclerView.ViewHolder(view), LayoutContainer {

    companion object {
        fun inflate(parent: ViewGroup): AboutViewHolder {
            val view = parent.inflate(R.layout.view_holder_pokemon_about)
            return AboutViewHolder(view)
        }
    }

    override val containerView = itemView

    @SuppressLint("DefaultLocale")
    fun bind(pokemon: Pokemon, species: Species) {
        txtValueGenus?.text = species.genus
        txtValueEggGroups?.text = species.eggGroups.joinToString(", ").capitalize()
        txtValueShape?.text = species.shape
        txtValueHeight?.text = itemView.resources.getString(R.string.pokemon_height_value, pokemon.height)
        txtValueWeight?.text = itemView.resources.getString(R.string.pokemon_weight_value, pokemon.weight)
        txtValueAbilities?.text = pokemon.abilities
            .sortedBy { it.isHidden }
            .joinToString(", ") {
                if (it.isHidden) {
                    itemView.resources.getString(R.string.pokemon_abilities_hidden_value, it.name)
                } else {
                    it.name
                }
            }.capitalize()
    }

}