package me.scraplesh.courses.lib

import me.scraplesh.courses.domain.services.AuthService
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

const val HEADER_AUTHORIZATION = "Authorization"
const val BEARER = "Bearer"

class Oauth2Interceptor @Inject constructor(private val authService: AuthService) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        var request = chain.request()

        val accessToken = authService.getAccessToken()
        if (accessToken != null && request.url.pathSegments.none { it == "register" }) {
            request = request.newBuilder()
                .header(HEADER_AUTHORIZATION, "$BEARER $accessToken")
                .build()
        }

        return chain.proceed(request)
    }
}
