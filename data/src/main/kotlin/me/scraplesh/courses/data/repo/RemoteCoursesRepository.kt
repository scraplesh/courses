package me.scraplesh.courses.data.repo

import me.scraplesh.courses.domain.model.Course
import me.scraplesh.courses.domain.repo.CoursesRepository

class RemoteCoursesRepository : CoursesRepository {
    override suspend fun getCourses(): List<Course> {
        TODO("Not yet implemented")
    }
}
