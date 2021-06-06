package ru.emba.cbs.data

import kotlinx.coroutines.flow.Flow
import ru.emba.cbs.data.model.AuthCredentials
import okhttp3.MediaType
import okhttp3.RequestBody
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part

interface CbsAuthApi {
    @Multipart
    @POST
    fun auth(
        @Part("username") userName: RequestBody, // ,"test@test.com")
        @Part("password") password: RequestBody, // ,"111111111")
        @Part("grant_type") grantType: RequestBody = RequestBody.create(MediaType.parse("text/plain"), "password"),
        @Part("client_id") clientId: RequestBody = RequestBody.create(MediaType.parse("text/plain"), "2"),
        @Part("client_secret") clientSecret: RequestBody = RequestBody.create(MediaType.parse("text/plain"), "skK7JN8G9vupooO5qlrvXXFPRyh4uEnLv7kdrgE1"),
        @Part("scope") scope: RequestBody = RequestBody.create(MediaType.parse("text/plain"), "*"),
    ): Flow<AuthCredentials>

    @Multipart
    @POST
    fun refreshToken(
        @Part("refresh_token") refreshToken: RequestBody, // ,"test@test.com")
        @Part("grant_type") grantType: RequestBody = RequestBody.create(MediaType.parse("text/plain"), "password"),
        @Part("client_id") clientId: RequestBody = RequestBody.create(MediaType.parse("text/plain"), "2"),
        @Part("client_secret") clientSecret: RequestBody = RequestBody.create(MediaType.parse("text/plain"), "skK7JN8G9vupooO5qlrvXXFPRyh4uEnLv7kdrgE1"),
        @Part("scope") scope: RequestBody = RequestBody.create(MediaType.parse("text/plain"), "*"),
    ): Flow<AuthCredentials>
}