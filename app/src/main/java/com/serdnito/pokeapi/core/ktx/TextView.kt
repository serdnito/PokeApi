package com.serdnito.pokeapi.core.ktx

import android.graphics.Color
import android.widget.TextView

/**
 * [TextView] extensions
 */
fun TextView.setTextColor(hexColor: String) {
    val textColor = Color.parseColor(hexColor)
    setTextColor(textColor)
}