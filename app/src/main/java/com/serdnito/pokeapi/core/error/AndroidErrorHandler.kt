package com.serdnito.pokeapi.core.error

import android.content.Context
import com.serdnito.pokeapi.R
import javax.inject.Inject

class AndroidErrorHandler @Inject constructor(
    private val context: Context
) : ErrorHandler {

    override fun convert(error: Throwable) =
        when (error.cause) {
            is Error.NetworkError -> context.getString(R.string.error_network)
            is Error.ServerError -> context.getString(R.string.error_server)
            else -> context.getString(R.string.error_default)
        }

}