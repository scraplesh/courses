package ru.emba.cbs.data.repo

import javax.inject.Inject

class CoursesUserRepository @Inject constructor() :
    ru.emba.cbs.domain.repo.UserRepository {
    override suspend fun signIn(email: String, password: String) {
//         TODO("Not yet implemented")
    }

    override suspend fun updateUser(
        email: String,
        name: String,
        lastName: String?,
        patronymic: String?
    ) {
        TODO("Not yet implemented")
    }

    override suspend fun signUp(email: String, password: String, name: String) {
//        TODO("Not yet implemented")
    }

    override fun getUserInfo(): ru.emba.cbs.domain.model.UserInfo? {
        TODO("Not yet implemented")
    }
}