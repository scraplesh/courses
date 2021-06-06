package ru.emba.cbs.data.services

import javax.inject.Inject

class CbsAuthService @Inject constructor() : ru.emba.cbs.domain.services.AuthService {
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