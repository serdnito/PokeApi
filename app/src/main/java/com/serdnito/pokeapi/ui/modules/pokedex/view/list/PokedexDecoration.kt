package com.serdnito.pokeapi.ui.modules.pokedex.view.list

import android.content.res.Resources
import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.serdnito.pokeapi.R

class PokedexDecoration(resources: Resources) : RecyclerView.ItemDecoration() {

    private val margin = resources.getDimensionPixelSize(R.dimen.margin)

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        val itemPosition = parent.getChildAdapterPosition(view)
        when {
            unknownPosition(itemPosition) -> return
            isFirstItem(itemPosition) -> outRect.set(margin, margin, margin, margin)
            isLastItem(itemPosition, state.itemCount) -> outRect.set(margin, 0, margin, margin)
            else -> outRect.set(margin, 0, margin, margin)
        }
    }

    private fun isFirstItem(position: Int) = (position == 0)

    private fun isLastItem(position: Int, count: Int) = (count > 0) && (position == count - 1)

    private fun unknownPosition(position: Int) = (position == RecyclerView.NO_POSITION)

}