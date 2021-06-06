package ru.emba.cbs.features.signup

import ru.emba.cbs.features.signup.SignUpFeature.Event
import ru.emba.cbs.features.signup.SignUpFeature.Intention
import ru.emba.cbs.features.signup.SignUpUi.Reaction
import ru.emba.cbs.features.signup.SignUpUi.UiState
import ru.emba.cbs.mvi.MviBindings
import ru.emba.cbs.navigation.AppNavEvent.SignUpNavEvent
import ru.emba.cbs.navigation.Coordinator
import javax.inject.Inject

class SignUpMviBindings @Inject constructor(
    private val coordinator: Coordinator
) : MviBindings<SignUpUi, SignUpViewModel>() {
    override fun setup(view: SignUpUi, viewModel: SignUpViewModel) {
        bind(view to viewModel using { reaction ->
            when (reaction) {
                Reaction.BackClicked -> Intention.NavigateBack
                is Reaction.EmailChanged -> Intention.ChangeEmail(reaction.email)
                is Reaction.NameChanged -> Intention.ChangeName(reaction.name)
                is Reaction.PasswordChanged -> Intention.ChangePassword(reaction.password)
                Reaction.CreateAccountClicked -> Intention.SignUp
                Reaction.PrivacyPolicyClicked -> Intention.ShowPrivacyPolicy
                is Reaction.EmailFieldFocusLost -> Intention.CheckEmailValidity(reaction.email)
            }
        })
        bind(viewModel to view using { state ->
            when {
                state.isLoading -> UiState.Loading
                state.errors.isNotEmpty() -> UiState.Error(state.errors)
                else -> UiState.Content(state.email, state.name, state.password)
            }
        })
        bind(viewModel.events to coordinator using { event ->
            when (event) {
                Event.NavigatedBack -> SignUpNavEvent.NavigatedBack
                Event.SingedUp -> SignUpNavEvent.SignedUp
                Event.PrivacyPolicyRequested -> SignUpNavEvent.PrivacyPolicyRequested
            }
        })
    }
}
