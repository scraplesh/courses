package ru.emba.cbs.features.onboarding

import kotlinx.coroutines.flow.flowOf
import ru.emba.cbs.features.onboarding.OnboardingFeature.Event
import ru.emba.cbs.features.onboarding.OnboardingFeature.Intention
import ru.emba.cbs.mvi.ActorReducerFeature
import javax.inject.Inject

class OnboardingFeature @Inject constructor() : ActorReducerFeature<Intention, Unit, Unit, Event>(
    initialState = Unit,
    actor = { _, _ -> flowOf(Unit) },
    reducer = { _, _ -> },
    eventEmitter = { wish, _, _ ->
        when (wish) {
            Intention.ShowRegistration -> Event.SignUpRequested
            Intention.ShowLogin -> Event.SignInRequested
        }
    },
) {
    enum class Intention { ShowRegistration, ShowLogin, }
    enum class Event { SignUpRequested, SignInRequested, }
}
