package com.serdnito.pokeapi.ui.modules.pokedex.view.list

import android.content.res.Resources
import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.serdnito.pokeapi.R
import com.serdnito.pokeapi.core.widget.recyclerview.Decoration

class PokedexDecoration(resources: Resources) : Decoration() {

    private val marginLarge = resources.getDimensionPixelSize(R.dimen.margin_large)
    private val marginSmall = resources.getDimensionPixelSize(R.dimen.margin_small)

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        val itemPosition = parent.getChildAdapterPosition(view)
        when {
            unknownPosition(itemPosition) -> return
            isFirstItem(itemPosition) -> outRect.set(marginLarge, marginSmall, marginLarge, marginSmall)
            isLastItem(itemPosition, state.itemCount) -> outRect.set(marginLarge, 0, marginLarge, marginSmall)
            else -> outRect.set(marginLarge, 0, marginLarge, marginSmall)
        }
    }

}