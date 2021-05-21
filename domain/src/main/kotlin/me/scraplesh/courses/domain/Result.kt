package me.scraplesh.courses.domain.usecases

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf

sealed interface Result<out Data> {
    data class Value<out Data>(val data: Data) : Result<Data>
    data class Failure<out Data>(val error: Error) : Result<Data>
}

class Error(
    val code: Int,
    message: String? = null,
    cause: Throwable? = null
) : Throwable(message, cause)

fun <T> Error.toResult(): Result<T> = Result.Failure(this)

fun <T> Throwable.toErrorResult(): Result<T> = when (this) {
    is Error -> Result.Failure(this)
    else -> Result.Failure(Error(UNKNOWN, message, this))
}

fun <T> Result<T>.toDataFlow(): Flow<T> {
    return when (this) {
        is Result.Value -> flowOf(this.data)
        is Result.Failure -> flow { throw error }
    }
}

fun <T> Result<T>.toFlow(): Flow<Result<T>> = when (this) {
    is Result.Value -> flowOf(this)
    is Result.Failure -> flow { throw error }
}

fun <T> T.toResult(): Result<T> = Result.Value(this)

// unknown error
const val UNKNOWN = 99000