package ru.emba.cbs.features.courses.list

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import ru.emba.cbs.domain.usecases.GetCoursesUseCase
import ru.emba.cbs.features.courses.list.CoursesFeature.Effect
import ru.emba.cbs.features.courses.list.CoursesFeature.Event
import ru.emba.cbs.features.courses.list.CoursesFeature.Intention
import ru.emba.cbs.features.courses.list.CoursesFeature.State
import ru.emba.cbs.mvi.Actor
import ru.emba.cbs.mvi.ActorReducerFeature
import javax.inject.Inject

class CoursesFeature @Inject constructor(getCoursesUseCase: GetCoursesUseCase) :
    ActorReducerFeature<Intention, Effect, State, Event>(
        State.Loading,
        actor = CoursesActor(getCoursesUseCase = getCoursesUseCase),
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
        class ShowCourse(val course: ru.emba.cbs.domain.model.Course) : Intention()
    }

    sealed class Effect {
        object NoEffect : Effect()
        object StartedLoading : Effect()
        object ErrorLoading : Effect()
        class CoursesLoaded(val courses: List<ru.emba.cbs.domain.model.Course>) : Effect()
        object NoCoursesLoaded : Effect()
    }

    sealed class State {
        object Loading : State()
        class Content(val courses: List<ru.emba.cbs.domain.model.Course>) : State()
        object Empty : State()
    }

    sealed class Event {
        class ShowCourse(val course: ru.emba.cbs.domain.model.Course) : Event()
    }

    private class CoursesActor(private val getCoursesUseCase: GetCoursesUseCase) :
        Actor<Intention, State, Effect> {

        override fun invoke(intention: Intention, state: State): Flow<Effect> = when (intention) {
            Intention.GetCourses -> getCourses()
            is Intention.ShowCourse -> noEffect()
        }

        private fun noEffect(): Flow<Effect> = flowOf(Effect.NoEffect)

        private fun getCourses(): Flow<Effect> {
            return getCoursesUseCase().map { courses ->
                if (courses.isNotEmpty()) Effect.CoursesLoaded(courses)
                else Effect.NoCoursesLoaded
            }
                .onStart { emit(Effect.StartedLoading) }
                .catch { emit(Effect.ErrorLoading) }
        }
    }
}
