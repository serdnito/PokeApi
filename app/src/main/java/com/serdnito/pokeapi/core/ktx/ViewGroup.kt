package com.serdnito.pokeapi.core.ktx

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes

/**
 * [ViewGroup] extensions
 */
fun ViewGroup.inflate(@LayoutRes layoutResId: Int): View =
    LayoutInflater.from(context).inflate(layoutResId, this, false)