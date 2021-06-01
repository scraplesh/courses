package me.scraplesh.courses.features.timemanagement

import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import kotlinx.coroutines.flow.flowOf
import me.scraplesh.courses.domain.model.Chapter
import me.scraplesh.courses.domain.model.Course
import me.scraplesh.courses.features.timemanagement.TimeManagementFeature.Effect
import me.scraplesh.courses.features.timemanagement.TimeManagementFeature.Event
import me.scraplesh.courses.features.timemanagement.TimeManagementFeature.Intention
import me.scraplesh.courses.features.timemanagement.TimeManagementFeature.State
import me.scraplesh.courses.mvi.ActorReducerFeature

class TimeManagementFeature @AssistedInject constructor(@Assisted course: Course) :
    ActorReducerFeature<Intention, Effect, State, Event>(
        initialState = State(course),
        actor = { action, state ->
            when (action) {
                is Intention.ToggleChapter -> {
                    flowOf(
                        Effect.ChaptersCollapseStateUpdated(
                            state.chapters
                                .map { (chapter, collapseState) ->
                                    when {
                                        chapter == action.chapter &&
                                                collapseState == ChapterCollapseState.Collapsed -> {
                                            chapter to ChapterCollapseState.Expanded
                                        }
                                        chapter == action.chapter &&
                                                collapseState == ChapterCollapseState.Expanded -> {
                                            chapter to ChapterCollapseState.Collapsed
                                        }
                                        else -> chapter to collapseState
                                    }
                                }
                        )
                    )
                }
                Intention.Close -> flowOf(Effect.NoEffect)
            }
        },
        reducer = { state, effect ->
            when (effect) {
                is Effect.ChaptersCollapseStateUpdated -> state.copy(chapters = effect.chapters)
                Effect.NoEffect -> state
            }
        },
        eventEmitter = { action, _, _ ->
            when (action) {
                Intention.Close -> Event.Closed
                else -> null
            }
        }
    ) {
    sealed interface Intention {
        object Close : Intention
        class ToggleChapter(val chapter: Chapter) : Intention
    }

    sealed interface Effect {
        object NoEffect : Effect
        class ChaptersCollapseStateUpdated(
            val chapters: List<Pair<Chapter, ChapterCollapseState>>
        ) : Effect
    }

    data class State(
        val course: Course,
        val chapters: List<Pair<Chapter, ChapterCollapseState>> = course.chapters
            .map { chapter ->
                val chapterCollapseState =
                    if (chapter.chapters.isEmpty()) ChapterCollapseState.None
                    else ChapterCollapseState.Collapsed
                chapter to chapterCollapseState
            }
    )

    enum class Event { Closed }

    @dagger.assisted.AssistedFactory
    interface AssistedFactory {
        fun create(course: Course): TimeManagementFeature
    }
}
