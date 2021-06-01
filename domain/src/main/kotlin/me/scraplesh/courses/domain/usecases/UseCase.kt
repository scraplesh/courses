package me.scraplesh.courses.domain.usecases

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

interface Args
object EmptyArgs : Args

interface UseCase<A : Args, R> {
    operator fun invoke(args: A? = null): Flow<R>
}

abstract class SingleUseCase<A : Args, R> : UseCase<A, R> {
    abstract suspend fun single(args: A?): R
    suspend fun single(): R = single(null)

    override operator fun invoke(args: A?): Flow<R> {
        return flow { emit(single(args)) }
    }
}