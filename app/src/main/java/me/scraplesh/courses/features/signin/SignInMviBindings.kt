package me.scraplesh.courses.features.signin

import me.scraplesh.courses.features.signin.SignInUi.Reaction
import me.scraplesh.courses.features.signin.SignInFeature.Intention
import me.scraplesh.courses.features.signin.SignInFeature.Event
import me.scraplesh.courses.navigation.AppNavEvent.SignInNavEvent
import me.scraplesh.courses.mvi.MviBindings
import me.scraplesh.courses.navigation.Coordinator
import javax.inject.Inject

class SignInMviBindings @Inject constructor(
    private val coordinator: Coordinator
) : MviBindings<SignInUi, SignInViewModel>() {
    override fun setup(view: SignInUi, viewModel: SignInViewModel) {
        bind(view to viewModel using { reaction ->
            when (reaction) {
                Reaction.SignInClicked -> Intention.SignIn
                Reaction.BackClicked -> Intention.NavigateBack
                is Reaction.EmailChanged -> Intention.ChangeEmail(reaction.email)
                is Reaction.PasswordChanged -> Intention.ChangePassword(reaction.password)
                Reaction.RestoreAccountClicked -> Intention.RestoreAccount
                Reaction.RetryClicked -> Intention.SignIn
            }
        })
        bind(viewModel.events to coordinator using { event ->
            when (event) {
                Event.NavigatedBack -> SignInNavEvent.NavigatedBack
                Event.RestoreAccountRequested -> SignInNavEvent.ShowPasswordRecovery
                Event.SingInCompleted -> SignInNavEvent.SignedIn
            }
        })
    }
}
