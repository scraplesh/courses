package me.scraplesh.courses.features.signup

import me.scraplesh.courses.features.signup.SignUpUi.Reaction
import me.scraplesh.courses.features.signup.SignUpViewModel.Event
import me.scraplesh.courses.features.signup.SignUpViewModel.Intention
import me.scraplesh.courses.mvi.MviBindings
import me.scraplesh.courses.navigation.Coordinator
import me.scraplesh.courses.navigation.AppNavEvent.SignUpNavEvent
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
            }
        })
        bind(viewModel.events to coordinator using { event ->
            when (event) {
                Event.NavigatedBack -> SignUpNavEvent.NavigatedBack
                Event.SingedUp -> SignUpNavEvent.SignedUp
            }
        })
    }
}
