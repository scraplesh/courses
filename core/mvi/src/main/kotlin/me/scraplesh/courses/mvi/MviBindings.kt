package me.scraplesh.courses.mvi

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.mapNotNull
import kotlinx.coroutines.flow.onEach

typealias Connector<In, Out> = Triple<Flow<In>, FlowCollector<Out>, (In) -> Out?>

abstract class MviBindings<View, ViewModel> {
    private lateinit var scope: CoroutineScope

    fun setup(scope: CoroutineScope, view: View, viewModel: ViewModel) {
        this.scope = scope
        setup(view, viewModel)
    }

    abstract fun setup(view: View, viewModel: ViewModel)

    fun <In, Out> bind(connection: Connector<In, Out>) {
        val (flow, collector, transformer) = connection
        flow.mapNotNull { transformer(it) }
            .onEach { collector.emit(it) }
            .launchIn(scope)
    }

    protected infix fun <In, Out> Pair<Flow<In>, FlowCollector<Out>>.using(transformer: (In) -> Out?) =
        Triple(first, second, transformer)
}