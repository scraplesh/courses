package ru.emba.cbs.domain.repo

interface CoursesRepository {
    suspend fun getCourses(): List<ru.emba.cbs.domain.model.Course>
}
