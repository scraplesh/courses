package ru.emba.cbs.domain.usecases

import javax.inject.Inject

class SignUpUseCase @Inject constructor(private val repo: ru.emba.cbs.domain.repo.UserRepository) :
    SingleUseCase<SignUpUseCase.SignUpArgs, Unit>() {

    data class SignUpArgs(val email: String, val password: String, val name: String) : Args

    override suspend fun single(args: SignUpArgs?) {
        return with(requireNotNull(args)) { repo.signUp(email, password, name) }
    }


}
