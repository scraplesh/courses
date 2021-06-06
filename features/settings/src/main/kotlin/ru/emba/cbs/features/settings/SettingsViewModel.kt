package ru.emba.cbs.features.settings

import dagger.hilt.android.lifecycle.HiltViewModel
import ru.emba.cbs.features.settings.SettingsFeature.Effect
import ru.emba.cbs.features.settings.SettingsFeature.Event
import ru.emba.cbs.features.settings.SettingsFeature.Intention
import ru.emba.cbs.features.settings.SettingsFeature.State
import ru.emba.cbs.mvi.ActorReducerViewModel
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(feature: SettingsFeature) :
    ActorReducerViewModel<Intention, Effect, State, Event>(feature)