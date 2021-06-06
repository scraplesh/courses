package ru.emba.cbs.domain.usecases

import javax.inject.Inject

class SignInUseCase @Inject constructor(private val repo: ru.emba.cbs.domain.repo.UserRepository) :
    SingleUseCase<SignInUseCase.SignInArgs, Unit>() {

    data class SignInArgs(val email: String, val password: String) : Args

    override suspend fun single(args: SignInArgs?) {
        with(requireNotNull(args)) { repo.signIn(email, password) }
    }

}
