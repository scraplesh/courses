package ru.emba.cbs.features.timemanagement

import ru.emba.cbs.features.timemanagement.TimeManagementUi.Reaction
import ru.emba.cbs.features.timemanagement.TimeManagementUi.UiState
import ru.emba.cbs.features.timemanagement.TimeManagementFeature.Event
import ru.emba.cbs.features.timemanagement.TimeManagementFeature.Intention
import ru.emba.cbs.mvi.MviBindings
import ru.emba.cbs.navigation.Coordinator
import ru.emba.cbs.navigation.AppNavEvent.TimeManagementNavEvent
import javax.inject.Inject

class TimeManagementMviBindings @Inject constructor(
    private val coordinator: Coordinator
) : MviBindings<TimeManagementUi, TimeManagementViewModel>() {
    override fun setup(view: TimeManagementUi, viewModel: TimeManagementViewModel) {
        bind(view to viewModel using { reaction ->
            when (reaction) {
                is Reaction.ChapterClicked -> Intention.ToggleChapter(reaction.chapter)
                Reaction.CloseClicked -> Intention.Close
            }
        })
        bind(viewModel to view using { newState ->
            
            UiState(
                items = emptyList(),
                progress = newState.course.progress
            )
        })
        bind(viewModel.events to coordinator using { event ->
            when (event) {
                Event.Closed -> TimeManagementNavEvent.Close
            }
        })
    }
}
