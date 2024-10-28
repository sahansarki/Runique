package com.plcoding.wear.run.data

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking

fun main() = runBlocking {
    flow {
        emit("a")
        emit("a1")
        delay(200)
        emit("a2")
        emit("a3")
        emit("b")
        emit("son")
    }.flatMapLatest { value ->
        flow {
            emit(value)
            delay(500)
            emit(value + "_last")
        }
    }.collect {
        println(it)
    }
}