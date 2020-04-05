package com.serdnito.pokeapi.core.usecase

import com.serdnito.pokeapi.core.executor.Executor
import io.reactivex.Single

abstract class RxSingleUseCase<I, O>(
    private val executor: Executor
) : UseCase<I, Single<O>>() {

    operator fun invoke(input: I): Single<O> =
        build(input)
            .subscribeOn(executor.io)
            .observeOn(executor.main)

}