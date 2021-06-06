package me.scraplesh.courses.domain.services

interface AuthService {
    fun getAccessToken(): String?
    fun refreshAccessToken(): String?
    fun logout()
    fun notifyUserLoggedOut()
}
