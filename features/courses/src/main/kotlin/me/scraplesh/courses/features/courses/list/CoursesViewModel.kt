package me.scraplesh.courses.features.courses.list

import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import me.scraplesh.courses.domain.model.Course
import me.scraplesh.courses.domain.usecases.GetCoursesUseCase
import me.scraplesh.courses.features.courses.list.CoursesViewModel.Effect
import me.scraplesh.courses.features.courses.list.CoursesViewModel.Intention
import me.scraplesh.courses.features.courses.list.CoursesViewModel.Event
import me.scraplesh.courses.features.courses.list.CoursesViewModel.State
import me.scraplesh.courses.mvi.Actor
import me.scraplesh.courses.mvi.ActorReducerViewModel
import javax.inject.Inject

@HiltViewModel
class CoursesViewModel @Inject constructor(getCoursesInteractor: GetCoursesUseCase) :
    ActorReducerViewModel<Intention, Effect, State, Event>(
        State.Loading,
        actor = CoursesActor(getCoursesInteractor = getCoursesInteractor),
        reducer = { state, effect ->
            when (effect) {
                is Effect.CoursesLoaded -> State.Content(effect.courses)
                Effect.StartedLoading -> State.Loading
                Effect.ErrorLoading -> State.Empty
                Effect.NoEffect -> state
                Effect.NoCoursesLoaded -> State.Empty
            }
        },
        eventEmitter = { wish, _, _ ->
            when (wish) {
                is Intention.ShowCourse -> Event.ShowCourse(wish.course)
                else -> null
            }
        }
    ) {

    sealed class Intention {
        object GetCourses : Intention()
        class ShowCourse(val course: Course) : Intention()
    }

    sealed class Effect {
        object NoEffect : Effect()
        object StartedLoading : Effect()
        object ErrorLoading : Effect()
        class CoursesLoaded(val courses: List<Course>) : Effect()
        object NoCoursesLoaded : Effect()
    }

    sealed class State {
        object Loading : State()
        class Content(val courses: List<Course>) : State()
        object Empty : State()
    }

    sealed class Event {
        class ShowCourse(val course: Course) : Event()
    }

    private class CoursesActor(private val getCoursesInteractor: GetCoursesUseCase) :
        Actor<Intention, State, Effect> {

        override fun invoke(intention: Intention, state: State): Flow<Effect> = when (intention) {
            Intention.GetCourses -> getCourses()
            is Intention.ShowCourse -> noEffect()
        }

        private fun noEffect(): Flow<Effect> = flowOf(Effect.NoEffect)

        private fun getCourses(): Flow<Effect> {
            return flow {
                try {
                    emit(Effect.StartedLoading)
                    val courses = getCoursesInteractor()
                    emit(
                        if (courses.isNotEmpty()) Effect.CoursesLoaded(courses)
                        else Effect.NoCoursesLoaded
                    )
                } catch (e: Throwable) {
                    emit(Effect.ErrorLoading)
                }
            }
        }

    }
}