package me.scraplesh.courses.features.settings

import me.scraplesh.courses.features.settings.SettingsFeature.Effect
import me.scraplesh.courses.features.settings.SettingsFeature.Event
import me.scraplesh.courses.features.settings.SettingsFeature.Intention
import me.scraplesh.courses.features.settings.SettingsFeature.State
import me.scraplesh.courses.mvi.ActorReducerViewModel

class SettingsViewModel(feature: SettingsFeature) :
    ActorReducerViewModel<Intention, Effect, State, Event>(feature)