package me.scraplesh.courses.features.onboarding

import me.scraplesh.courses.features.onboarding.OnboardingFeature.Event
import me.scraplesh.courses.features.onboarding.OnboardingFeature.Intention
import me.scraplesh.courses.features.onboarding.OnboardingUi.Reaction
import me.scraplesh.courses.mvi.MviBindings
import me.scraplesh.courses.navigation.AppNavEvent.OnboardingNavEvent
import me.scraplesh.courses.navigation.Coordinator
import javax.inject.Inject

class OnboardingMviBindings @Inject constructor(
    private val coordinator: Coordinator
) : MviBindings<OnboardingUi, OnboardingViewModel>() {
    override fun setup(view: OnboardingUi, viewModel: OnboardingViewModel) {
        bind(view to viewModel using { reaction ->
            when (reaction) {
                Reaction.SignInClicked -> Intention.ShowLogin
                Reaction.SignUpClicked -> Intention.ShowRegistration
            }
        })
        bind(viewModel.events to coordinator using { event ->
            when (event) {
                Event.SignInRequested -> OnboardingNavEvent.ShowSignIn
                Event.SignUpRequested -> OnboardingNavEvent.ShowSignUp
            }
        })
    }
}
