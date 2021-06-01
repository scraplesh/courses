package me.scraplesh.courses.domain.usecases

import me.scraplesh.courses.domain.model.Course
import me.scraplesh.courses.domain.repo.CoursesRepository
import javax.inject.Inject

class GetCoursesUseCase @Inject constructor(private val repo: CoursesRepository) :
    SingleUseCase<EmptyArgs, List<Course>>() {
    override suspend fun single(args: EmptyArgs?): List<Course> = repo.getCourses()
}
