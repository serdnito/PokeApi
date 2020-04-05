package com.serdnito.pokeapi.core.ktx

import io.reactivex.*
import io.reactivex.disposables.Disposable
import io.reactivex.functions.Action
import io.reactivex.functions.Consumer
import io.reactivex.internal.functions.Functions

private val onNextStub: (Any) -> Unit = {}
private val onErrorStub: (Throwable) -> Unit = {}
private val onCompleteStub: () -> Unit = {}

private fun <T : Any> ((T) -> Unit).asConsumer(): Consumer<T> {
    return if (this === onNextStub) Functions.emptyConsumer() else Consumer(this)
}

private fun ((Throwable) -> Unit).asOnErrorConsumer(): Consumer<Throwable> {
    return if (this === onErrorStub) Functions.ON_ERROR_MISSING else Consumer(this)
}

private fun (() -> Unit).asOnCompleteAction(): Action {
    return if (this === onCompleteStub) Functions.EMPTY_ACTION else Action(this)
}

/**
 * Overloaded subscribe function that allows passing named parameters
 */
fun <T : Any> Observable<T>.subscribeBy(
    onError: (Throwable) -> Unit = onErrorStub,
    onComplete: () -> Unit = onCompleteStub,
    onNext: (T) -> Unit = onNextStub
): Disposable =
    subscribe(onNext.asConsumer(), onError.asOnErrorConsumer(), onComplete.asOnCompleteAction())

/**
 * Overloaded subscribe function that allows passing named parameters
 */
fun <T : Any> Single<T>.subscribeBy(
    onError: (Throwable) -> Unit = onErrorStub,
    onSuccess: (T) -> Unit = onNextStub
): Disposable =
    subscribe(onSuccess.asConsumer(), onError.asOnErrorConsumer())

/**
 * Overloaded subscribe function that allows passing named parameters
 */
fun Completable.subscribeBy(
    onError: (Throwable) -> Unit = onErrorStub,
    onComplete: () -> Unit = onCompleteStub
): Disposable =
    when {
        // There are optimized versions of the completable Consumers, so we need to use the subscribe overloads here.
        onError === onErrorStub && onComplete === onCompleteStub -> subscribe()
        onError === onErrorStub -> subscribe(onComplete)
        else -> subscribe(onComplete.asOnCompleteAction(), Consumer(onError))
    }

fun <T> List<T>.flatMapCompletable(action: (List<T>) -> CompletableSource) =
    Single.create<List<T>> { it.onSuccess(this) }
        .flatMapCompletable { action(it) }

fun <T, R> T.mapSingle(action: (T) -> R) =
    Single.create<T> { it.onSuccess(this) }
        .map { action(it) }

fun <T> T.mapSingle() =
    Single.create<T> { it.onSuccess(this) }
