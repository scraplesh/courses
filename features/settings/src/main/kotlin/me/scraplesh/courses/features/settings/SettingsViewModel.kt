package me.scraplesh.courses.features.settings

import dagger.hilt.android.lifecycle.HiltViewModel
import me.scraplesh.courses.features.settings.SettingsFeature.Effect
import me.scraplesh.courses.features.settings.SettingsFeature.Event
import me.scraplesh.courses.features.settings.SettingsFeature.Intention
import me.scraplesh.courses.features.settings.SettingsFeature.State
import me.scraplesh.courses.mvi.ActorReducerViewModel
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(feature: SettingsFeature) :
    ActorReducerViewModel<Intention, Effect, State, Event>(feature)