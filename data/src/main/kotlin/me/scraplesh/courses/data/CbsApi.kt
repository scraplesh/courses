package me.scraplesh.courses.data

import kotlinx.coroutines.flow.Flow
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Query

interface CbsApi {
    @Multipart
    @POST("register")
    fun auth(@Body credentials: UserCredentials): Flow<UserJson>

    @GET("profile")
    fun profile(): Flow<UserJson>

    @PUT("profile")
    fun updateProfile(@Body user: UserJson): Flow<UserJson>

    @GET("my-courses")
    fun courses(): Flow<CoursesJson>

    @GET("programs/{programId}")
    fun program(@Query("programId") programId: String): Flow<ProgramJson>

    @GET("sidebar/{courseId}/{programId}")
    fun currentTask(
        @Query("courseId") courseId: String,
        @Query("programId") programId: String
    )

    data class UserCredentials(
        val email: String,
        val password: String,
        val firstName: String,
        val politics: String = "1",
        val newsLetter: String = "0"
    )

    object UserJson
    object CoursesJson
    object ProgramJson
}