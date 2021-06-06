package ru.emba.cbs.domain.services

interface AuthService {
    fun getAccessToken(): String?
    fun refreshAccessToken(): String?
    fun logout()
    fun notifyUserLoggedOut()
}
