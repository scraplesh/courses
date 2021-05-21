package me.scraplesh.courses.domain.usecases

import me.scraplesh.courses.domain.repo.UserRepository

class SignInUseCase(private val repo: UserRepository) :
    BaseUseCase<SignInUseCase.SignInArgs, Unit>() {

    data class SignInArgs(val email: String, val password: String) : Args

    override suspend fun invoke(args: SignInArgs?) {
        with(requireNotNull(args)) { repo.signIn(email, password) }
    }

}
