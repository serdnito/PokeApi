package com.serdnito.pokeapi.ui.di

import android.content.Context
import com.serdnito.pokeapi.BuildConfig
import com.serdnito.pokeapi.core.interceptor.NetworkInterceptor
import com.serdnito.pokeapi.data.api.PokeApi
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
class NetworkModule {

    @Provides
    @Singleton
    fun provideApi(retrofit: Retrofit): PokeApi =
        retrofit.create(PokeApi::class.java)

    @Provides
    @Singleton
    fun provideClient(context: Context): OkHttpClient =
        OkHttpClient.Builder()
            .readTimeout(30L, TimeUnit.SECONDS)
            .connectTimeout(30L, TimeUnit.SECONDS)
            .addInterceptor(getLoggingInterceptor())
            .addInterceptor(getNetworkInterceptor(context))
            .build()

    @Provides
    @Singleton
    fun provideGson(): Gson =
        GsonBuilder()
            .setDateFormat("yyyy-MM-dd'T'hh:mm:ss'Z'")
            .setPrettyPrinting()
            .create()

    @Provides
    @Singleton
    fun provideRetrofit(gson: Gson, okHttpClient: OkHttpClient): Retrofit =
        Retrofit.Builder()
            .baseUrl(BuildConfig.API_URL)
            .client(okHttpClient)
            .addCallAdapterFactory(getRetrofitCallAdapter())
            .addConverterFactory(getRetrofitConverter(gson))
            .build()

    private fun getLoggingInterceptor() =
        HttpLoggingInterceptor().apply {
            level = if (BuildConfig.DEBUG) {
                HttpLoggingInterceptor.Level.BODY
            } else {
                HttpLoggingInterceptor.Level.NONE
            }
        }

    private fun getNetworkInterceptor(context: Context) =
        NetworkInterceptor(context)

    private fun getRetrofitCallAdapter() =
        RxJava2CallAdapterFactory.create()

    private fun getRetrofitConverter(gson: Gson) =
        GsonConverterFactory.create(gson)

}