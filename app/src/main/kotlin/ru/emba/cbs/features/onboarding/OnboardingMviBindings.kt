package ru.emba.cbs.features.onboarding

import ru.emba.cbs.features.onboarding.OnboardingFeature.Event
import ru.emba.cbs.features.onboarding.OnboardingFeature.Intention
import ru.emba.cbs.features.onboarding.OnboardingUi.Reaction
import ru.emba.cbs.mvi.MviBindings
import ru.emba.cbs.navigation.AppNavEvent.OnboardingNavEvent
import ru.emba.cbs.navigation.Coordinator
import javax.inject.Inject

class OnboardingMviBindings @Inject constructor(
    private val coordinator: Coordinator
) : MviBindings<OnboardingUi, OnboardingViewModel>() {
    override fun setup(view: OnboardingUi, viewModel: OnboardingViewModel) {
        bind(viewModel to view using {
            OnboardingUi.UiState(
                items = listOf(
                    OnboardingUi.Item(
                        title = R.string.onboarding_title1,
                        description = R.string.onboarding_description1
                    ),
                OnboardingUi.Item(
                        title = R.string.onboarding_title2,
                        description = R.string.onboarding_description2
                    ),
                OnboardingUi.Item(
                        title = R.string.onboarding_title3,
                        description = R.string.onboarding_description3
                    ),
                )
            )
        })
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
