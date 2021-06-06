package ru.emba.cbs.features.course

import ru.emba.cbs.features.course.CourseFeature.Event
import ru.emba.cbs.mvi.MviBindings
import ru.emba.cbs.navigation.AppNavEvent.CourseNavEvent
import ru.emba.cbs.navigation.Coordinator
import javax.inject.Inject

class CourseMviBindings @Inject constructor(
    private val coordinator: Coordinator
) : MviBindings<CourseUi, CourseViewModel>() {
    override fun setup(view: CourseUi, viewModel: CourseViewModel) {
        bind(viewModel.events to coordinator using { event ->
            when (event) {
                Event.NavigatedBack -> CourseNavEvent.NavigatedBack
                is Event.TimeManagementRequested -> CourseNavEvent.ShowTimeManagement(event.course)
            }
        })
    }
}
