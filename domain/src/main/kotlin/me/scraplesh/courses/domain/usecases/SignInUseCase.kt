package me.scraplesh.courses.domain.usecases

import me.scraplesh.courses.domain.repo.UserRepository
import javax.inject.Inject

class SignInUseCase @Inject constructor(private val repo: UserRepository) :
    SingleUseCase<SignInUseCase.SignInArgs, Unit>() {

    data class SignInArgs(val email: String, val password: String) : Args

    override suspend fun invoke(args: SignInArgs?) {
        with(requireNotNull(args)) { repo.signIn(email, password) }
    }

}
