package com.serdnito.pokeapi.ui.modules.pokedex.view.detail

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.serdnito.pokeapi.R
import com.serdnito.pokeapi.core.ktx.inflate
import com.serdnito.pokeapi.domain.model.Pokemon
import kotlinx.android.extensions.LayoutContainer

class EvolutionViewHolder(view: View) : RecyclerView.ViewHolder(view), LayoutContainer {

    companion object {
        fun inflate(parent: ViewGroup): EvolutionViewHolder {
            val view = parent.inflate(R.layout.fragment_under_construction)
            return EvolutionViewHolder(view)
        }
    }

    override val containerView = itemView

    fun bind(model: Pokemon) {

    }

}