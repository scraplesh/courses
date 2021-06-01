package me.scraplesh.courses.features.signup

import dagger.hilt.android.lifecycle.HiltViewModel
import me.scraplesh.courses.features.signup.SignUpFeature.Effect
import me.scraplesh.courses.features.signup.SignUpFeature.Event
import me.scraplesh.courses.features.signup.SignUpFeature.Intention
import me.scraplesh.courses.features.signup.SignUpFeature.State
import me.scraplesh.courses.mvi.ActorReducerViewModel
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(feature: SignUpFeature) :
    ActorReducerViewModel<Intention, Effect, State, Event>(feature)