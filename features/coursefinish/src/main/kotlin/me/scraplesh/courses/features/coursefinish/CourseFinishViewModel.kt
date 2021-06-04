package me.scraplesh.courses.features.coursefinish

import dagger.hilt.android.lifecycle.HiltViewModel
import me.scraplesh.courses.features.coursefinish.CourseFinishFeature.Effect
import me.scraplesh.courses.features.coursefinish.CourseFinishFeature.Event
import me.scraplesh.courses.features.coursefinish.CourseFinishFeature.Intention
import me.scraplesh.courses.features.coursefinish.CourseFinishFeature.State
import me.scraplesh.courses.mvi.ActorReducerViewModel
import javax.inject.Inject

@HiltViewModel
class CourseFinishViewModel @Inject constructor(feature: CourseFinishFeature) :
    ActorReducerViewModel<Intention, Effect, State, Event>(feature)
