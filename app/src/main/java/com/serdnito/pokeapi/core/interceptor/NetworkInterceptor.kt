package com.serdnito.pokeapi.core.interceptor

import android.content.Context
import com.serdnito.pokeapi.core.error.Error
import com.serdnito.pokeapi.core.ktx.isOnline
import okhttp3.Interceptor
import okhttp3.Interceptor.Chain
import okhttp3.Response
import java.io.IOException
import java.net.SocketTimeoutException

/**
 * An interceptor that checks if the device is online and the timeout for webservices connections.
 */
class NetworkInterceptor(private val context: Context) : Interceptor {

    @Throws(IOException::class)
    override fun intercept(chain: Chain): Response {
        if (context.isOnline()) {
            return try {
                val request = chain.request()
                chain.proceed(request)
            } catch (e: SocketTimeoutException) {
                throw Error.ServerError
            }
        }
        throw Error.NetworkError
    }

}