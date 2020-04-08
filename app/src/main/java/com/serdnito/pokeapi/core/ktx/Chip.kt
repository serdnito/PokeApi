package com.serdnito.pokeapi.core.ktx

import android.content.res.ColorStateList
import android.graphics.Color
import com.google.android.material.chip.Chip
import kotlin.math.roundToInt

/**
 * [Chip] extensions
 */
fun Chip.setCheckedBgColor(hexColor: String) {
    val color = Color.parseColor(hexColor)
    val halfTransparentColor = Color.argb(
        (Color.alpha(color) * 0.75).roundToInt(),
        Color.red(color),
        Color.green(color),
        Color.blue(color)
    )
    chipBackgroundColor = ColorStateList(
        arrayOf(intArrayOf(android.R.attr.state_checked)),
        intArrayOf(halfTransparentColor)
    )
}