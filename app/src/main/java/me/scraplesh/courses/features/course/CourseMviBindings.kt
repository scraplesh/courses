package me.scraplesh.courses.features.course

import me.scraplesh.courses.features.course.CourseViewModel.Event
import me.scraplesh.courses.mvi.MviBindings
import me.scraplesh.courses.navigation.Coordinator
import me.scraplesh.courses.navigation.CoursesNavEvent.CourseNavEvent
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
