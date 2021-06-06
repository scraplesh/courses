package ru.emba.cbs.features.onboarding

import dagger.hilt.android.lifecycle.HiltViewModel
import ru.emba.cbs.features.onboarding.OnboardingFeature.Effect
import ru.emba.cbs.features.onboarding.OnboardingFeature.Event
import ru.emba.cbs.features.onboarding.OnboardingFeature.Intention
import ru.emba.cbs.features.onboarding.OnboardingFeature.State
import ru.emba.cbs.mvi.ActorReducerViewModel
import javax.inject.Inject

@HiltViewModel
class OnboardingViewModel @Inject constructor(feature: OnboardingFeature) :
    ActorReducerViewModel<Intention, Effect, State, Event>(feature)
