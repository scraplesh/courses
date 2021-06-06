package ru.emba.cbs.features.coursefinish

import dagger.hilt.android.lifecycle.HiltViewModel
import ru.emba.cbs.features.coursefinish.CourseFinishFeature.Effect
import ru.emba.cbs.features.coursefinish.CourseFinishFeature.Event
import ru.emba.cbs.features.coursefinish.CourseFinishFeature.Intention
import ru.emba.cbs.features.coursefinish.CourseFinishFeature.State
import ru.emba.cbs.mvi.ActorReducerViewModel
import javax.inject.Inject

@HiltViewModel
class CourseFinishViewModel @Inject constructor(feature: CourseFinishFeature) :
    ActorReducerViewModel<Intention, Effect, State, Event>(feature)
