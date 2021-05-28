package me.scraplesh.courses.data.repo

import me.scraplesh.courses.domain.repo.UserRepository
import javax.inject.Inject

class CoursesUserRepository @Inject constructor() : UserRepository {
    override suspend fun signIn(email: String, password: String) {
        TODO("Not yet implemented")
    }

    override suspend fun updateUser(
        email: String,
        name: String,
        lastName: String,
        patronymic: String
    ) {
        TODO("Not yet implemented")
    }

    override suspend fun signUp(email: String, password: String, name: String) {
        TODO("Not yet implemented")
    }
}