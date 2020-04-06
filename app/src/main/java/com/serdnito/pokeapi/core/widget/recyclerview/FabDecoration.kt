package com.serdnito.pokeapi.core.widget.recyclerview

import android.content.res.Resources
import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.serdnito.pokeapi.R

class FabDecoration(resources: Resources) : Decoration() {

    private val marginBottom = resources.getDimensionPixelSize(R.dimen.fab_decoration_offset)

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        val itemPosition = parent.getChildAdapterPosition(view)
        if (isLastItem(itemPosition, state.itemCount)) {
            outRect.bottom = marginBottom
        }
    }

}