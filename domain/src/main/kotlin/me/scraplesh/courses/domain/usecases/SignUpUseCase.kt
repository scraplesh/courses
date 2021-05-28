package me.scraplesh.courses.domain.usecases

import me.scraplesh.courses.domain.repo.UserRepository
import javax.inject.Inject

class SignUpUseCase @Inject constructor(private val repo: UserRepository) :
    BaseUseCase<SignUpUseCase.SignUpArgs, Unit>() {

    data class SignUpArgs(val email: String, val password: String, val name: String) : Args

    override suspend fun invoke(args: SignUpArgs?) {
        return with(requireNotNull(args)) { repo.signUp(email, password, name) }
    }


}
