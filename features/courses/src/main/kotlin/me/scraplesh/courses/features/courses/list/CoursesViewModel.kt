package me.scraplesh.courses.features.courses.list

import dagger.hilt.android.lifecycle.HiltViewModel
import me.scraplesh.courses.features.courses.list.CoursesFeature.Effect
import me.scraplesh.courses.features.courses.list.CoursesFeature.Event
import me.scraplesh.courses.features.courses.list.CoursesFeature.Intention
import me.scraplesh.courses.features.courses.list.CoursesFeature.State
import me.scraplesh.courses.mvi.ActorReducerViewModel
import javax.inject.Inject

@HiltViewModel
class CoursesViewModel @Inject constructor(feature: CoursesFeature) :
    ActorReducerViewModel<Intention, Effect, State, Event>(feature)
