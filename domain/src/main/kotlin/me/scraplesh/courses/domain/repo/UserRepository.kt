package me.scraplesh.courses.domain.repo

interface UserRepository {
    suspend fun signIn(email: String, password: String)
    suspend fun signUp(email: String, password: String, name: String)
    suspend fun updateUser(email: String, name: String, lastName: String, patronymic: String)
}
