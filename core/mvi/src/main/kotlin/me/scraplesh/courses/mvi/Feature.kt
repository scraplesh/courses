package me.scraplesh.courses.mvi

import android.util.Log
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

typealias IntentionToAction<Intention, Action> = (intention: Intention) -> Action
typealias Bootstrapper<Action> = () -> Flow<Action>
typealias Actor<Action, State, Effect> = (action: Action, state: State) -> Flow<Effect>
typealias Reducer<State, Effect> = (state: State, effect: Effect) -> State
typealias EventEmitter<Action, Effect, State, Event> = (action: Action, effect: Effect, state: State) -> Event?
typealias PostProcessor<Action, Effect, State> = (action: Action, effect: Effect, state: State) -> Action?

interface Feature<Action, Event, State> : FlowCollector<Action>, StateFlow<State> {
    val events: Flow<Event>
}

class MviFeature<Intention, Action, Effect, State, Event> private constructor(
    scope: CoroutineScope,
    states: MutableStateFlow<State>,
    private val intentionToAction: IntentionToAction<Intention, Action>,
    bootstrapper: Bootstrapper<Action>? = null,
    actor: Actor<Action, State, Effect>,
    reducer: Reducer<State, Effect>,
    eventEmitter: EventEmitter<Action, Effect, State, Event>?,
    postProcessor: PostProcessor<Action, Effect, State>?
) : Feature<Intention, Event, State>, StateFlow<State> by states, CoroutineScope by scope {
    constructor(
        scope: CoroutineScope,
        initialState: State,
        intentionToAction: IntentionToAction<Intention, Action>,
        bootstrapper: Bootstrapper<Action>? = null,
        actor: Actor<Action, State, Effect>,
        reducer: Reducer<State, Effect>,
        eventEmitter: EventEmitter<Action, Effect, State, Event>? = null,
        postProcessor: PostProcessor<Action, Effect, State>? = null
    ) : this(
        scope = scope,
        states = MutableStateFlow(initialState),
        intentionToAction = intentionToAction,
        bootstrapper = bootstrapper,
        actor = actor,
        reducer = reducer,
        eventEmitter = eventEmitter,
        postProcessor = postProcessor
    )

    override val events: Flow<Event> = MutableSharedFlow()
    private val actions = MutableSharedFlow<Action>()

    init {
        actions.onStart { bootstrapper?.invoke()?.let { emitAll(it) } }
            .flatMapLatest { action ->
                actor(action, value).map { effect ->
                    Log.d(LOG_TAG, "Effect: $effect")
                    action to effect
                }
            }
            .onEach { (action, effect) ->
                states.value = reducer(value, effect)
                Log.d(LOG_TAG, "State: $value")

                eventEmitter?.invoke(action, effect, value)?.let {
                    Log.d(LOG_TAG, "Event: $it")
                    launch { (events as MutableSharedFlow).emit(it) }
                }
                postProcessor?.invoke(action, effect, value)?.let {
                    Log.d(LOG_TAG, "Post process: $it")
                    launch { actions.emit(it) }
                }
            }
            .launchIn(this)
    }

    override suspend fun emit(value: Intention) {
        actions.emit(intentionToAction(value))
    }

    private companion object {
        const val LOG_TAG = "MVI"
    }
}

abstract class MviViewModel<Intention, Action, Effect, State, Event> private constructor(
    private val scope: CoroutineScope,
    initialState: State,
    intentionToAction: IntentionToAction<Intention, Action>,
    bootstrapper: Bootstrapper<Action>? = null,
    actor: Actor<Action, State, Effect>,
    reducer: Reducer<State, Effect>,
    eventEmitter: EventEmitter<Action, Effect, State, Event>?,
    postProcessor: PostProcessor<Action, Effect, State>?
) : ViewModel(), Feature<Intention, Event, State> by MviFeature(
    scope = scope,
    initialState = initialState,
    intentionToAction = intentionToAction,
    bootstrapper = bootstrapper,
    actor = actor,
    reducer = reducer,
    eventEmitter = eventEmitter,
    postProcessor = postProcessor
) {
    constructor(
        initialState: State,
        intentionToAction: IntentionToAction<Intention, Action>,
        bootstrapper: Bootstrapper<Action>? = null,
        actor: Actor<Action, State, Effect>,
        reducer: Reducer<State, Effect>,
        eventEmitter: EventEmitter<Action, Effect, State, Event>?,
        postProcessor: PostProcessor<Action, Effect, State>?
    ) : this(
        scope = CoroutineScope(SupervisorJob() + Dispatchers.Main.immediate),
        initialState = initialState,
        intentionToAction = intentionToAction,
        bootstrapper = bootstrapper,
        actor = actor,
        reducer = reducer,
        eventEmitter = eventEmitter,
        postProcessor = postProcessor
    )

    override fun onCleared() {
        scope.coroutineContext.cancel()
    }
}

abstract class ActorReducerViewModel<Intention, Effect, State, Event>(
    initialState: State,
    bootstrapper: Bootstrapper<Intention>? = null,
    actor: Actor<Intention, State, Effect>,
    reducer: Reducer<State, Effect>,
    eventEmitter: EventEmitter<Intention, Effect, State, Event>? = null,
    postProcessor: PostProcessor<Intention, Effect, State>? = null
) : MviViewModel<Intention, Intention, Effect, State, Event>(
    initialState = initialState,
    intentionToAction = { it },
    bootstrapper = bootstrapper,
    actor = actor,
    reducer = reducer,
    eventEmitter = eventEmitter,
    postProcessor = postProcessor
)

abstract class StatelessViewModel<Intention, Event>(
    eventEmitter: EventEmitter<Intention, Unit, Unit, Event>
) : ActorReducerViewModel<Intention, Unit, Unit, Event>(
    initialState = Unit,
    actor = { _, _ -> flowOf(Unit) },
    reducer = { _, _ -> },
    eventEmitter = eventEmitter
)