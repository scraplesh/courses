package ru.emba.cbs.data.repo

import javax.inject.Inject

class RemoteCoursesRepository @Inject constructor() :
    ru.emba.cbs.domain.repo.CoursesRepository {
    override suspend fun getCourses(): List<ru.emba.cbs.domain.model.Course> {
        TODO("Not yet implemented")
    }
}
