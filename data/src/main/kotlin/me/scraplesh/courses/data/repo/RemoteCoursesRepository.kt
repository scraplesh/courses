package me.scraplesh.courses.data.repo

import me.scraplesh.courses.domain.model.Course
import me.scraplesh.courses.domain.repo.CoursesRepository
import javax.inject.Inject

class RemoteCoursesRepository @Inject constructor() : CoursesRepository {
    override suspend fun getCourses(): List<Course> {
        TODO("Not yet implemented")
    }
}
