package ru.emba.cbs.features.courses

import ru.emba.cbs.features.courses.list.CoursesUi
import ru.emba.cbs.features.courses.list.CoursesUi.Reaction
import ru.emba.cbs.features.courses.list.CoursesViewModel
import ru.emba.cbs.features.courses.list.CoursesFeature.Event
import ru.emba.cbs.features.courses.list.CoursesFeature.Intention
import ru.emba.cbs.mvi.MviBindings
import ru.emba.cbs.navigation.AppNavEvent.CoursesNavEvent
import ru.emba.cbs.navigation.Coordinator
import javax.inject.Inject

class CoursesMviBindings @Inject constructor(
    private val coordinator: Coordinator
) : MviBindings<CoursesUi, CoursesViewModel>() {
    override fun setup(view: CoursesUi, viewModel: CoursesViewModel) {
        bind(viewModel.events to coordinator using { event ->
            when (event) {
                is Event.ShowCourse -> CoursesNavEvent.ShowCourse(event.course)
            }
        })
        bind(view to viewModel using { reaction ->
            when (reaction) {
                is Reaction.CourseClicked -> Intention.ShowCourse(reaction.course)
            }
        })
    }
}
