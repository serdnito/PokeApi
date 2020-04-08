package com.serdnito.pokeapi.core.ktx

import android.content.res.ColorStateList
import android.widget.ImageView
import androidx.annotation.ColorInt
import androidx.core.widget.ImageViewCompat

fun ImageView.tint(@ColorInt color: Int) {
    ImageViewCompat.setImageTintList(this, ColorStateList.valueOf(color))
}