package ru.emba.cbs.domain.usecases

import javax.inject.Inject

class GetCoursesUseCase @Inject constructor(private val repo: ru.emba.cbs.domain.repo.CoursesRepository) :
    SingleUseCase<EmptyArgs, List<ru.emba.cbs.domain.model.Course>>() {
    override suspend fun single(args: EmptyArgs?): List<ru.emba.cbs.domain.model.Course> = repo.getCourses()
}
