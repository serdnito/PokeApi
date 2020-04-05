package com.serdnito.pokeapi.core.usecase

import com.serdnito.pokeapi.core.executor.Executor
import io.reactivex.Completable

abstract class RxCompletableUseCase<in T>(
    private val executor: Executor
) {

    abstract fun build(input: T): Completable

    operator fun invoke(input: T): Completable =
        build(input)
            .subscribeOn(executor.io)
            .observeOn(executor.main)

}