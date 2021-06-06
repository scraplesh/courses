package ru.emba.cbs.domain.usecases

import ru.emba.cbs.domain.model.UserInfo
import ru.emba.cbs.domain.repo.UserRepository
import javax.inject.Inject

class GetUserInfoUseCase @Inject constructor(private val repo: UserRepository) :
    SingleUseCase<EmptyArgs, UserInfo>() {

    override suspend fun single(args: EmptyArgs?): UserInfo =
        repo.getUserInfo() ?: error("Missing user info")
}
