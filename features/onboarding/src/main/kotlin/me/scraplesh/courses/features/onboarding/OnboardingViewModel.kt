package me.scraplesh.courses.features.onboarding

import dagger.hilt.android.lifecycle.HiltViewModel
import me.scraplesh.courses.features.onboarding.OnboardingFeature.Effect
import me.scraplesh.courses.features.onboarding.OnboardingFeature.Event
import me.scraplesh.courses.features.onboarding.OnboardingFeature.Intention
import me.scraplesh.courses.features.onboarding.OnboardingFeature.State
import me.scraplesh.courses.mvi.ActorReducerViewModel
import javax.inject.Inject

@HiltViewModel
class OnboardingViewModel @Inject constructor(feature: OnboardingFeature) :
    ActorReducerViewModel<Intention, Effect, State, Event>(feature)
