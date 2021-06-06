package ru.emba.cbs.domain.usecases

import javax.inject.Inject

class UpdateUserUseCase @Inject constructor(private val repo: ru.emba.cbs.domain.repo.UserRepository) :
    SingleUseCase<UpdateUserUseCase.UpdateUserArgs, Unit>() {

    data class UpdateUserArgs(
        val email: String,
        val name: String,
        val lastName: String?,
        val patronymic: String?
    ) : Args

    override suspend fun single(args: UpdateUserArgs?) =
        with(requireNotNull(args)) { repo.updateUser(email, name, lastName, patronymic) }
}
