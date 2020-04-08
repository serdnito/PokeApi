package com.serdnito.pokeapi.ui.modules.pokedex.view.detail

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.serdnito.pokeapi.domain.model.Pokemon
import com.serdnito.pokeapi.domain.model.Species

class PokemonAdapter(
    private val pokemon: Pokemon,
    private val species: Species
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun getItemCount() = 4

    override fun getItemViewType(position: Int) = position

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
        when (viewType) {
            0 -> AboutViewHolder.inflate(parent)
            1 -> StatsViewHolder.inflate(parent)
            2 -> EvolutionViewHolder.inflate(parent)
            else -> MovesViewHolder.inflate(parent)
        }

    override fun onBindViewHolder(viewHolder: RecyclerView.ViewHolder, position: Int) {
        when(viewHolder) {
            is AboutViewHolder -> viewHolder.bind(pokemon, species)
            is StatsViewHolder -> viewHolder.bind(pokemon.stats)
            is EvolutionViewHolder -> viewHolder.bind(pokemon)
            is MovesViewHolder -> viewHolder.bind(pokemon)
        }
    }

}