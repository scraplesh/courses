package ru.emba.cbs.mvi

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.transform
import kotlinx.coroutines.launch

abstract class Ui<Reaction, State, View> private constructor(
    protected val states: MutableSharedFlow<State>,
    private val reactions: MutableSharedFlow<Reaction>
) : FlowCollector<State> by states,
    Flow<Reaction> by reactions {

    constructor() : this(
        states = MutableSharedFlow<State>(
            replay = 1,
            onBufferOverflow = BufferOverflow.DROP_OLDEST
        ),
        reactions = MutableSharedFlow(),
    )

    val state: State? get() = states.replayCache.firstOrNull()
    private lateinit var scope: CoroutineScope

    fun bindViews(scope: CoroutineScope, view: View) {
        this.scope = scope
        bindViews(view)
    }

    protected abstract fun bindViews(view: View)

    protected fun react(reaction: Reaction) {
        scope.launch { reactions.emit(reaction) }
    }

    protected fun <T> Flow<T>.react(action: suspend (T) -> Reaction): Job {
        return transform { value ->
            react(action(value))
            return@transform emit(value)
        }.launchIn(scope)
    }

    protected fun <T> Flow<T>.subscribe(action: suspend (T) -> Unit): Job =
        onEach(action).launchIn(scope)
}
