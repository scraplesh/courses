package me.scraplesh.courses.features.settings

import me.scraplesh.courses.features.settings.SettingsUi.Reaction
import me.scraplesh.courses.features.settings.SettingsUi.UiState
import me.scraplesh.courses.features.settings.SettingsFeature.Event
import me.scraplesh.courses.features.settings.SettingsFeature.Intention
import me.scraplesh.courses.features.settings.SettingsFeature.State
import me.scraplesh.courses.mvi.MviBindings
import me.scraplesh.courses.navigation.Coordinator
import me.scraplesh.courses.navigation.AppNavEvent.SettingsNavEvent
import javax.inject.Inject

class SettingsMviBindings @Inject constructor(
    private val coordinator: Coordinator
) : MviBindings<SettingsUi, SettingsViewModel>() {
    override fun setup(view: SettingsUi, viewModel: SettingsViewModel) {
        bind(viewModel.events to coordinator using { event ->
            when (event) {
                Event.NavigatedBack -> SettingsNavEvent.NavigateBack
                Event.SignedOut -> SettingsNavEvent.SignedOut
                else -> null
            }
        })
        bind(view to viewModel using { reaction ->
            when (reaction) {
                Reaction.BackClicked -> Intention.NavigateBack
                Reaction.ChangePasswordClicked -> Intention.ChangePassword
                Reaction.ContactsClicked -> Intention.ShowContacts
                Reaction.SaveClicked -> Intention.UpdateUser
                Reaction.SignOutClicked -> Intention.SignOut
                is Reaction.EmailChanged -> Intention.ChangeEmail(reaction.email)
                is Reaction.LastNameChanged -> Intention.ChangeLastName(reaction.lastName)
                is Reaction.NameChanged -> Intention.ChangeName(reaction.name)
                is Reaction.PatronymicChanged -> Intention.ChangePatronymic(reaction.patronymic)
            }
        })
        bind(viewModel to view using { newState ->
            when (newState) {
                is State.Content -> UiState.Content(
                    email = newState.email,
                    name = newState.name,
                    lastName = newState.lastName ?: "",
                    patronymic = newState.patronymic ?: ""
                )
                State.Error -> UiState.Error
                State.Loading -> UiState.Loading
            }
        })
        bind(viewModel.events to coordinator using { event ->
            when (event) {
                Event.NavigatedBack -> TODO()
                Event.ChangePasswordRequested -> TODO()
                Event.ShowContactsRequested -> TODO()
                Event.SignedOut -> TODO()
            }
        })
    }
}
