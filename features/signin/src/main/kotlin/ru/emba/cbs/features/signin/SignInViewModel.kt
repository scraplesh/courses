package ru.emba.cbs.features.signin

import dagger.hilt.android.lifecycle.HiltViewModel
import ru.emba.cbs.features.signin.SignInFeature.Effect
import ru.emba.cbs.features.signin.SignInFeature.Event
import ru.emba.cbs.features.signin.SignInFeature.Intention
import ru.emba.cbs.features.signin.SignInFeature.State
import ru.emba.cbs.mvi.ActorReducerViewModel
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor(feature: SignInFeature) :
    ActorReducerViewModel<Intention, Effect, State, Event>(feature)