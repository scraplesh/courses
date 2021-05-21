package me.scraplesh.courses.domain.usecases

import me.scraplesh.courses.domain.model.Course
import me.scraplesh.courses.domain.repo.CoursesRepository

class GetCoursesUseCase(private val repo: CoursesRepository) :
    BaseUseCase<EmptyArgs, List<Course>>() {
    override suspend fun invoke(args: EmptyArgs?): List<Course> = repo.getCourses()
}
