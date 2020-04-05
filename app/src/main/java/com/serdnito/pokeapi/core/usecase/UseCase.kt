package com.serdnito.pokeapi.core.usecase

abstract class UseCase<in I, out O> {
    abstract fun build(input: I): O
}