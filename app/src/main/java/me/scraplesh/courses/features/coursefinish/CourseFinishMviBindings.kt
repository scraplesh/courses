package me.scraplesh.courses.features.coursefinish

import me.scraplesh.courses.mvi.MviBindings
import me.scraplesh.courses.navigation.Coordinator
import javax.inject.Inject

class CourseFinishMviBindings @Inject constructor(
    private val coordinator: Coordinator
) : MviBindings<CourseFinishUi, CourseFinishViewModel>() {
    override fun setup(view: CourseFinishUi, viewModel: CourseFinishViewModel) {
    }
}
