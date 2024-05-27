package com.plcoding.core.domain.util

// For sealed interface

sealed interface Result<out D, out E : Error> {
    data class Success<out D>(val data: D) : Result<D, Nothing>
    data class Error<out E : com.plcoding.core.domain.util.Error>(val error: E) : Result<Nothing, E>

}

inline fun <T, E : Error, R> Result<T, E>.map(map: (T) -> R): Result<R, E> {
    return when (this) {
        is Result.Error -> {
            Result.Error(error)
        }

        is Result.Success -> {
            Result.Success(map(data))
        }
    }
}

fun <T, E : Error> Result<T, E>.asEmptyDataResult(): EmptyResult<E> {
    return this.map {

    }
}

typealias EmptyResult<E> = Result<Unit, E>



// For sealed class

sealed class ResultWithSealedClass<out D, out E : Error> {
    data class Success<out D>(val data: D) : ResultWithSealedClass<D, Nothing>()
    data class Error<out E : com.plcoding.core.domain.util.Error>(val error: E) :
        ResultWithSealedClass<Nothing, E>()
}

inline fun <T, E : Error, R> ResultWithSealedClass<T, E>.map(map: (T) -> R): ResultWithSealedClass<R, E> {
    return when (this) {
        is ResultWithSealedClass.Error -> {
            ResultWithSealedClass.Error(error)
        }

        is ResultWithSealedClass.Success -> {
            ResultWithSealedClass.Success(map(data))
        }
    }
}

fun <T, E : Error> ResultWithSealedClass<T, E>.asEmptyDataResult(): EmptyDataResultForSealedClass<E> {
    return this.map { }
}

typealias EmptyDataResultForSealedClass<E> = ResultWithSealedClass<Unit, E>
