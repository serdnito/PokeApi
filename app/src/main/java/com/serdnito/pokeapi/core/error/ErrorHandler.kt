package com.serdnito.pokeapi.core.error

interface ErrorHandler {
    fun convert(error: Throwable): String
}