package ru.emba.cbs.features.coursefinish

import kotlinx.coroutines.flow.flowOf
import ru.emba.cbs.features.coursefinish.CourseFinishFeature.Effect
import ru.emba.cbs.features.coursefinish.CourseFinishFeature.Event
import ru.emba.cbs.features.coursefinish.CourseFinishFeature.Intention
import ru.emba.cbs.features.coursefinish.CourseFinishFeature.State
import ru.emba.cbs.mvi.ActorReducerFeature
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
