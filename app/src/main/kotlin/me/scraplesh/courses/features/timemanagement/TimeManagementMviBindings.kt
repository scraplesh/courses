package me.scraplesh.courses.features.timemanagement

import me.scraplesh.courses.features.timemanagement.TimeManagementUi.Reaction
import me.scraplesh.courses.features.timemanagement.TimeManagementUi.UiState
import me.scraplesh.courses.features.timemanagement.TimeManagementFeature.Event
import me.scraplesh.courses.features.timemanagement.TimeManagementFeature.Intention
import me.scraplesh.courses.mvi.MviBindings
import me.scraplesh.courses.navigation.Coordinator
import me.scraplesh.courses.navigation.AppNavEvent.TimeManagementNavEvent
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
