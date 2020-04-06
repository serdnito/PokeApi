package com.serdnito.pokeapi.core.ktx

import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

/**
 * [RecyclerView] extensions
 */
fun RecyclerView.addOnBottomReachListener(onBottomReach: () -> Unit) {
    addOnScrollListener(object : RecyclerView.OnScrollListener() {
        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
            if (isBottomReached(dy)) {
                onBottomReach()
            }
        }

        private fun isBottomReached(dy: Int): Boolean {
            if (dy > 0 && layoutManager != null) {
                val firstVisibleItem = if (layoutManager is LinearLayoutManager) {
                    (layoutManager as LinearLayoutManager).findFirstVisibleItemPosition()
                } else {
                    (layoutManager as GridLayoutManager).findFirstVisibleItemPosition()
                }
                return layoutManager!!.childCount + firstVisibleItem >= layoutManager!!.itemCount
            }
            return false
        }
    })
}

fun RecyclerView.addOnScrollListener(action: () -> Unit) {
    addOnScrollListener(object : RecyclerView.OnScrollListener() {
        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
            action()
        }
    })
}

fun RecyclerView.ItemDecoration.attachTo(recyclerView: RecyclerView) {
    recyclerView.addItemDecoration(this)
}