package ru.emba.cbs.features.courses

import ru.emba.cbs.features.courses.CoursesHostUi.Reaction
import ru.emba.cbs.mvi.MviBindings
import ru.emba.cbs.navigation.Coordinator
import ru.emba.cbs.navigation.AppNavEvent.CoursesHostNavEvent
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
