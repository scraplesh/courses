package me.scraplesh.courses.domain.repo

import me.scraplesh.courses.domain.model.Course

interface CoursesRepository {
    suspend fun getCourses(): List<Course>
}
