package com.serdnito.pokeapi.core.executor

import io.reactivex.Scheduler

interface Executor {
    val io: Scheduler
    val main: Scheduler
}