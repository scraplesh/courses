package me.scraplesh.courses.domain.usecases

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

interface Args
object EmptyArgs : Args

interface UseCase<A : Args, R> {
    fun execute(args: A? = null): Flow<Result<R>>
}

operator fun <A : Args, R> UseCase<A, R>.invoke(args: A?) = execute(args)
operator fun <A : Args, R> UseCase<A, R>.invoke() = execute(null)

abstract class BaseUseCase<A : Args, R> : UseCase<A, R> {
    override fun execute(args: A?): Flow<Result<R>> {
        return flow {
            emit(
                try {
                    invoke(args).toResult()
                } catch (e: Throwable) {
                    e.toErrorResult()
                }
            )
        }
    }

    abstract suspend operator fun invoke(args: A?): R
    suspend operator fun invoke(): R { return invoke(null) }
}