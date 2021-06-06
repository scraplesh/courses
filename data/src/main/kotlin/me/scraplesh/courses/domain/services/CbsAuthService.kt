package me.scraplesh.courses.domain.services

import javax.inject.Inject

class CbsAuthService @Inject constructor() : AuthService {
    override fun getAccessToken(): String? {
        TODO("Not yet implemented")
    }

    override fun refreshAccessToken(): String? {
        TODO("Not yet implemented")
    }

    override fun logout() {
        TODO("Not yet implemented")
    }

    override fun notifyUserLoggedOut() {
        TODO("Not yet implemented")
    }
}