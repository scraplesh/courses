package me.scraplesh.courses.features.signin

import dagger.hilt.android.lifecycle.HiltViewModel
import me.scraplesh.courses.features.signin.SignInFeature.Effect
import me.scraplesh.courses.features.signin.SignInFeature.Event
import me.scraplesh.courses.features.signin.SignInFeature.Intention
import me.scraplesh.courses.features.signin.SignInFeature.State
import me.scraplesh.courses.mvi.ActorReducerViewModel
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor(feature: SignInFeature) :
    ActorReducerViewModel<Intention, Effect, State, Event>(feature)