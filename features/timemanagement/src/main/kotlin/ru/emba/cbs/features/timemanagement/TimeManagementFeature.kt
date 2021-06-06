package ru.emba.cbs.features.timemanagement

import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import kotlinx.coroutines.flow.flowOf
import ru.emba.cbs.features.timemanagement.TimeManagementFeature.Effect
import ru.emba.cbs.features.timemanagement.TimeManagementFeature.Event
import ru.emba.cbs.features.timemanagement.TimeManagementFeature.Intention
import ru.emba.cbs.features.timemanagement.TimeManagementFeature.State
import ru.emba.cbs.mvi.ActorReducerFeature

class TimeManagementFeature @AssistedInject constructor(@Assisted course: ru.emba.cbs.domain.model.Course) :
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
        class ToggleChapter(val chapter: ru.emba.cbs.domain.model.Chapter) : Intention
    }

    sealed interface Effect {
        object NoEffect : Effect
        class ChaptersCollapseStateUpdated(
            val chapters: List<Pair<ru.emba.cbs.domain.model.Chapter, ChapterCollapseState>>
        ) : Effect
    }

    data class State(
        val course: ru.emba.cbs.domain.model.Course,
        val chapters: List<Pair<ru.emba.cbs.domain.model.Chapter, ChapterCollapseState>> = course.chapters
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
        fun create(course: ru.emba.cbs.domain.model.Course): TimeManagementFeature
    }
}
