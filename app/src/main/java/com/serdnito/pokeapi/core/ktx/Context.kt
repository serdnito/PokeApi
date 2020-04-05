package com.serdnito.pokeapi.core.ktx

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build

/**
 * [Context] extensions
 */
@Suppress("DEPRECATION")
fun Context.isOnline() =
    if (Build.VERSION.SDK_INT >= 23) {
        (getSystemService(Context.CONNECTIVITY_SERVICE) as? ConnectivityManager)?.let { connManager ->
            connManager.getNetworkCapabilities(connManager.activeNetwork)?.run {
                when {
                    hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
                    hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
                    hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
                    else -> false
                }
            } ?: false
        } ?: false
    } else {
        try {
            (getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager)
                .activeNetworkInfo // This may return null when there is no default network
                ?.isConnected ?: false
        } catch (e: Exception) {
            false
        }
    }