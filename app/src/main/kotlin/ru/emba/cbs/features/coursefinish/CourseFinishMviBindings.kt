package ru.emba.cbs.features.coursefinish

import ru.emba.cbs.mvi.MviBindings
import ru.emba.cbs.navigation.Coordinator
import javax.inject.Inject

class CourseFinishMviBindings @Inject constructor(
    private val coordinator: Coordinator
) : MviBindings<CourseFinishUi, CourseFinishViewModel>() {
    override fun setup(view: CourseFinishUi, viewModel: CourseFinishViewModel) {
    }
}
