package me.scraplesh.courses.mvi

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.cancel

abstract class MviViewModel<Intention, Action, Effect, State, Event>(
    private val feature: MviFeature<Intention, Action, Effect, State, Event>
) : ViewModel(), Feature<Intention, Event, State> by feature {
    override fun onCleared() {
        feature.cancel()
    }
}

abstract class ActorReducerViewModel<Intention, Effect, State, Event>(
    feature: MviFeature<Intention, Intention, Effect, State, Event>
) : MviViewModel<Intention, Intention, Effect, State, Event>(feature)