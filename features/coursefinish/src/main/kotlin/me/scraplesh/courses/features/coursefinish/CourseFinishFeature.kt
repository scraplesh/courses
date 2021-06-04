package me.scraplesh.courses.features.coursefinish

import kotlinx.coroutines.flow.flowOf
import me.scraplesh.courses.features.coursefinish.CourseFinishFeature.Effect
import me.scraplesh.courses.features.coursefinish.CourseFinishFeature.Event
import me.scraplesh.courses.features.coursefinish.CourseFinishFeature.Intention
import me.scraplesh.courses.features.coursefinish.CourseFinishFeature.State
import me.scraplesh.courses.mvi.ActorReducerFeature
import javax.inject.Inject

class CourseFinishFeature @Inject constructor() :
    ActorReducerFeature<Intention, Effect, State, Event>(
        initialState = State(),
        actor = { _, _ -> flowOf(Effect()) },
        reducer = { state, _ -> state },
    ) {

    class Intention
    class Effect
    class State
    class Event
}
