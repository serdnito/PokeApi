package com.serdnito.pokeapi.ui.modules.pokedex.view.detail

import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.serdnito.pokeapi.R
import com.serdnito.pokeapi.core.ktx.inflate
import com.serdnito.pokeapi.domain.model.PokemonStat
import com.serdnito.pokeapi.domain.model.Stat
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.view_holder_pokemon_stats.*

class StatsViewHolder(view: View) : RecyclerView.ViewHolder(view), LayoutContainer {

    companion object {
        fun inflate(parent: ViewGroup): StatsViewHolder {
            val view = parent.inflate(R.layout.view_holder_pokemon_stats)
            return StatsViewHolder(view)
        }
    }

    override val containerView = itemView

    fun bind(stats: List<PokemonStat>) {
        val hp = stats.find { it.stat == Stat.Hp }
        bindStat(hp, txtValueHp, barHp)
        val attack = stats.find { it.stat == Stat.Attack }
        bindStat(attack, txtValueAttack, barAttack)
        val defense = stats.find { it.stat == Stat.Defense }
        bindStat(defense, txtValueDefense, barDefense)
        val spAttack = stats.find { it.stat == Stat.SpecialAttack }
        bindStat(spAttack, txtValueSpAttack, barSpAttack)
        val spDefense = stats.find { it.stat == Stat.SpecialDefense }
        bindStat(spDefense, txtValueSpDefense, barSpDefense)
        val speed = stats.find { it.stat == Stat.Speed }
        bindStat(speed, txtValueSpeed, barSpeed)
    }

    private fun bindStat(stat: PokemonStat?, txtValue: TextView?, bar: ProgressBar?) {
        txtValue?.text = "${stat?.value ?: 0}"
        bar?.run {
            max = stat?.max ?: 0
            progress = stat?.value ?: 0
        }
    }

}