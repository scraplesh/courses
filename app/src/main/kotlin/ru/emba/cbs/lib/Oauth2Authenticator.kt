package ru.emba.cbs.lib

import okhttp3.Authenticator
import okhttp3.Request
import okhttp3.Response
import okhttp3.Route
import retrofit2.HttpException
import java.net.HttpURLConnection
import javax.inject.Inject

class Oauth2Authenticator @Inject constructor(
    private val authService: ru.emba.cbs.domain.services.AuthService,
) : Authenticator {
    private val accessToken: String? get() = authService.getAccessToken()

    override fun authenticate(route: Route?, response: Response): Request? {
        // We need to have a token in order to refresh it.
        val token = accessToken

        synchronized(this) {
            val newToken = accessToken

            // Check if the request made was previously made as an authenticated request.
            val request = response.request
            if (request.header(HEADER_AUTHORIZATION) != null) {
                // If the token has changed since the request was made, use the new token.
                if (newToken != null && newToken != token) {
                    return request.newBuilder()
                        .header(HEADER_AUTHORIZATION, "$BEARER $newToken")
                        .build()
                }

                try {
                    val refreshedToken = authService.refreshAccessToken()
                    // Retry the request with the new token.
                    return request.newBuilder()
                        .header(HEADER_AUTHORIZATION, "$BEARER $refreshedToken")
                        .build()
                } catch (error: Exception) {
                    // In case when the refresh token was expired
                    if (
                        error is HttpException &&
                        error.code() in listOf(HttpURLConnection.HTTP_UNAUTHORIZED)
                    ) {
                        authService.logout()
                        authService.notifyUserLoggedOut()
                    }
                }
            }
        }

        return null
    }
}
