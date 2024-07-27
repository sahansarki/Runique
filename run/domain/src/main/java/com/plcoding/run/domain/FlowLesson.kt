package com.plcoding.run.domain

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.zip
import kotlinx.coroutines.runBlocking

fun main() {
    //zipExp()
    combineExp()
}

fun zipExp() {
    runBlocking {
        val flow1 = flowOf(1, 2, 3, 4)
        val flow2 = flowOf("A", "B", "C")

        flow1.zip(flow2) { a, b ->
            "$a$b"
        }.collect { value ->
            println(value)
        }
    }
}

fun combineExp() {
    runBlocking {
        val flow1 = flow {
            emit(1)
            delay(5000L)
            emit(2)
        }

        val flow2 = flow {
            emit("A")
            delay(50)
            emit("B")
            emit("C")
            emit("D")
        }

        flow1.combine(flow2) { a, b ->
            "$a$b"
        }.collect { value ->
            println(value)
        }
    }
}