package me.scraplesh.courses.features.course

import me.scraplesh.courses.features.course.CourseFeature.Event
import me.scraplesh.courses.mvi.MviBindings
import me.scraplesh.courses.navigation.Coordinator
import me.scraplesh.courses.navigation.AppNavEvent.CourseNavEvent
import javax.inject.Inject

class CourseMviBindings @Inject constructor(
    private val coordinator: Coordinator
) : MviBindings<CourseUi, CourseFeature>() {
    override fun setup(view: CourseUi, feature: CourseFeature) {
        bind(feature.events to coordinator using { event ->
            when (event) {
                Event.NavigatedBack -> CourseNavEvent.NavigatedBack
                is Event.TimeManagementRequested -> CourseNavEvent.ShowTimeManagement(event.course)
            }
        })
    }
}
