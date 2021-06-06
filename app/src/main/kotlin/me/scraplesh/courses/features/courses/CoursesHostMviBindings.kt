package me.scraplesh.courses.features.courses

import me.scraplesh.courses.features.courses.CoursesHostUi.Reaction
import me.scraplesh.courses.mvi.MviBindings
import me.scraplesh.courses.navigation.Coordinator
import me.scraplesh.courses.navigation.AppNavEvent.CoursesHostNavEvent
import javax.inject.Inject

class CoursesHostMviBindings @Inject constructor(
    private val coordinator: Coordinator
) : MviBindings<CoursesHostUi, Unit>() {
    override fun setup(view: CoursesHostUi, viewModel: Unit) {
        bind(view to coordinator using { reaction ->
            when (reaction) {
                Reaction.SettingsClicked -> CoursesHostNavEvent.ShowSettings
                Reaction.BackClicked -> CoursesHostNavEvent.NavigatedBack
            }
        })
    }
}
