package me.scraplesh.courses.features.course

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import me.scraplesh.courses.domain.model.Course
import me.scraplesh.courses.domain.usecases.CheckNetworkAvailabilityUseCase
import me.scraplesh.courses.features.course.CourseViewModel.Effect
import me.scraplesh.courses.features.course.CourseViewModel.Event
import me.scraplesh.courses.features.course.CourseViewModel.Intention
import me.scraplesh.courses.features.course.CourseViewModel.State
import me.scraplesh.courses.mvi.Actor
import me.scraplesh.courses.mvi.ActorReducerViewModel

class CourseViewModel(
    private val course: Course,
    checkNetworkAvailabilityUseCase: CheckNetworkAvailabilityUseCase
) : ActorReducerViewModel<Intention, Effect, State, Event>(
    initialState = State.CheckingNetwork,
    bootstrapper = { flowOf(Intention.CheckNetworkAvailability) },
    actor = CourseActor(
        course.url,
        checkNetworkAvailabilityUseCase = checkNetworkAvailabilityUseCase,
    ),
    reducer = { state, effect ->
        when (effect) {
            Effect.LoadingStopped -> State.Content
            Effect.NetworkNotAvailable -> State.NoNetwork
            is Effect.CourseLoaded -> State.Loading(effect.courseUrl)
            Effect.NoEffect -> state
            Effect.NetworkAvailable -> state
        }
    },
    eventEmitter = { action, _, _ ->
        when (action) {
            Intention.NavigateBack -> Event.NavigatedBack
            Intention.ShowTimeManagement -> Event.TimeManagementRequested
            else -> null
        }
    },
    postProcessor = { _, effect, _ ->
        when (effect) {
            Effect.NetworkAvailable -> Intention.LoadCourse
            else -> null
        }
    }
) {
    sealed interface State {
        object CheckingNetwork : State
        data class Loading(val url: String) : State
        object Content : State
        object NoNetwork : State
    }

    enum class Intention {
        NavigateBack,
        ShowTimeManagement,
        CheckNetworkAvailability,
        LoadCourse,
        StopShowingLoading,
    }

    sealed interface Effect {
        class CourseLoaded(val courseUrl: String) : Effect
        object NoEffect : Effect
        object LoadingStopped : Effect
        object NetworkAvailable : Effect
        object NetworkNotAvailable : Effect
    }

    enum class Event {
        NavigatedBack,
        TimeManagementRequested,
    }

    class CourseActor(
        private val courseUrl: String,
        private val checkNetworkAvailabilityUseCase: CheckNetworkAvailabilityUseCase,
    ) : Actor<Intention, State, Effect> {
        override fun invoke(intention: Intention, state: State): Flow<Effect> = when (intention) {
            Intention.StopShowingLoading -> stopLoading()
            Intention.CheckNetworkAvailability -> checkNetworkAvailability()
            Intention.LoadCourse -> flowOf(Effect.CourseLoaded(courseUrl))
            Intention.NavigateBack -> noEffect()
            Intention.ShowTimeManagement -> noEffect()
        }

        private fun checkNetworkAvailability(): Flow<Effect> {
            return flow {
                emit(
                    if (checkNetworkAvailabilityUseCase()) Effect.NetworkAvailable
                    else Effect.NetworkNotAvailable
                )
            }
        }

        private fun stopLoading(): Flow<Effect> = flowOf(Effect.LoadingStopped)

        private fun noEffect(): Flow<Effect> = flowOf(Effect.NoEffect)
    }
}