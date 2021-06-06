package ru.emba.cbs.domain.repo

interface UserRepository {
    suspend fun signIn(email: String, password: String)
    suspend fun signUp(email: String, password: String, name: String)
    suspend fun updateUser(email: String, name: String, lastName: String?, patronymic: String?)
    fun getUserInfo(): ru.emba.cbs.domain.model.UserInfo?
}
