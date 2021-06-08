package ru.emba.cbs.features.onboarding

import dagger.hilt.android.lifecycle.HiltViewModel
import ru.emba.cbs.features.onboarding.OnboardingFeature.Event
import ru.emba.cbs.features.onboarding.OnboardingFeature.Intention
import ru.emba.cbs.mvi.ActorReducerViewModel
import javax.inject.Inject

@HiltViewModel
class OnboardingViewModel @Inject constructor(feature: OnboardingFeature) :
    ActorReducerViewModel<Intention, Unit, Unit, Event>(feature)
