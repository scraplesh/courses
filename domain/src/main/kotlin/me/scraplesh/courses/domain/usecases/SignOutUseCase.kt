package me.scraplesh.courses.domain.usecases

import javax.inject.Inject

class SignOutUseCase @Inject constructor() : BaseUseCase<EmptyArgs, Unit>() {
    override suspend fun invoke(args: EmptyArgs?) = TODO("Not implemented yet")
}
