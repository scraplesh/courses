package me.scraplesh.courses.features.courses

import me.scraplesh.courses.features.courses.list.CoursesUi
import me.scraplesh.courses.features.courses.list.CoursesUi.Reaction
import me.scraplesh.courses.features.courses.list.CoursesViewModel
import me.scraplesh.courses.features.courses.list.CoursesViewModel.Event
import me.scraplesh.courses.features.courses.list.CoursesViewModel.Intention
import me.scraplesh.courses.mvi.MviBindings
import me.scraplesh.courses.navigation.AppNavEvent.CoursesNavEvent
import me.scraplesh.courses.navigation.Coordinator
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
