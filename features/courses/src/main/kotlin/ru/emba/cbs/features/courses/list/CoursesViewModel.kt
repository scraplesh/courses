package ru.emba.cbs.features.courses.list

import dagger.hilt.android.lifecycle.HiltViewModel
import ru.emba.cbs.features.courses.list.CoursesFeature.Effect
import ru.emba.cbs.features.courses.list.CoursesFeature.Event
import ru.emba.cbs.features.courses.list.CoursesFeature.Intention
import ru.emba.cbs.features.courses.list.CoursesFeature.State
import ru.emba.cbs.mvi.ActorReducerViewModel
import javax.inject.Inject

@HiltViewModel
class CoursesViewModel @Inject constructor(feature: CoursesFeature) :
    ActorReducerViewModel<Intention, Effect, State, Event>(feature)
