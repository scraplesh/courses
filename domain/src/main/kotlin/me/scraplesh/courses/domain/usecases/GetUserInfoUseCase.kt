package me.scraplesh.courses.domain.usecases

import me.scraplesh.courses.domain.model.UserInfo
import me.scraplesh.courses.domain.repo.UserRepository
import javax.inject.Inject

class GetUserInfoUseCase @Inject constructor(private val repo: UserRepository) :
    SingleUseCase<EmptyArgs, UserInfo>() {

    override suspend fun single(args: EmptyArgs?): UserInfo =
        repo.getUserInfo() ?: error("Missing user info")
}
