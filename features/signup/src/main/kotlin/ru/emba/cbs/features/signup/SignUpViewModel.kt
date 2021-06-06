package ru.emba.cbs.features.signup

import dagger.hilt.android.lifecycle.HiltViewModel
import ru.emba.cbs.features.signup.SignUpFeature.Effect
import ru.emba.cbs.features.signup.SignUpFeature.Event
import ru.emba.cbs.features.signup.SignUpFeature.Intention
import ru.emba.cbs.features.signup.SignUpFeature.State
import ru.emba.cbs.mvi.ActorReducerViewModel
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(feature: SignUpFeature) :
    ActorReducerViewModel<Intention, Effect, State, Event>(feature)