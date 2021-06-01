package me.scraplesh.courses.domain.usecases

import javax.inject.Inject

class SignOutUseCase @Inject constructor() : SingleUseCase<EmptyArgs, Unit>() {
    override suspend fun single(args: EmptyArgs?) = TODO("Not implemented yet")
}
