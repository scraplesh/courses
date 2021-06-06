package ru.emba.cbs.features.onboarding

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import ru.emba.cbs.features.onboarding.OnboardingFeature.Effect
import ru.emba.cbs.features.onboarding.OnboardingFeature.Event
import ru.emba.cbs.features.onboarding.OnboardingFeature.Intention
import ru.emba.cbs.features.onboarding.OnboardingFeature.State
import ru.emba.cbs.mvi.ActorReducerFeature
import javax.inject.Inject

class OnboardingFeature @Inject constructor() :
    ActorReducerFeature<Intention, Effect, State, Event>(
        initialState = State.FirstPage,
        actor = { intention, state ->
            when (intention) {
                Intention.ShowNextPage -> {
                    flow {
                        when (state) {
                            State.FirstPage -> {
                                emit(Effect.PageChanged(page = OnboardingPage.Second))
                                delay(DELAY_PAGE)
                                emit(Effect.NextPageShown)
                            }
                            State.SecondPage -> emit(Effect.PageChanged(page = OnboardingPage.Third))
                            State.ThirdPage -> emit(Effect.NoEffect)
                        }
                    }
                }
                Intention.ShowPreviousPage -> {
                    flow {
                        when (state) {
                            State.FirstPage -> emit(Effect.NoEffect)
                            State.SecondPage -> {
                                emit(Effect.PageChanged(page = OnboardingPage.First))
                                delay(DELAY_PAGE)
                                emit(Effect.NextPageShown)
                            }
                            State.ThirdPage -> {
                                emit(Effect.PageChanged(page = OnboardingPage.Second))
                                delay(DELAY_PAGE)
                                emit(Effect.NextPageShown)
                            }
                        }
                    }
                }
                else -> flowOf(Effect.NoEffect)
            }
        },
        reducer = { state, effect ->
            when (effect) {
                is Effect.PageChanged -> {
                    when (effect.page) {
                        OnboardingPage.First -> State.FirstPage
                        OnboardingPage.Second -> State.SecondPage
                        OnboardingPage.Third -> State.ThirdPage
                    }
                }
                else -> state
            }
        },
        eventEmitter = { wish, _, _ ->
            when (wish) {
                Intention.ShowRegistration -> Event.SignUpRequested
                Intention.ShowLogin -> Event.SignInRequested
                else -> null
            }
        },
        postProcessor = { _, effect, _ ->
            when (effect) {
                Effect.NextPageShown -> Intention.ShowNextPage
                else -> null
            }
        }
    ) {
    enum class Intention { ShowNextPage, ShowPreviousPage, ShowRegistration, ShowLogin }

    sealed interface Effect {
        object NoEffect : Effect
        object NextPageShown : Effect
        class PageChanged(val page: OnboardingPage) : Effect
    }

    enum class State { FirstPage, SecondPage, ThirdPage }
    enum class Event { SignUpRequested, SignInRequested }

    private companion object {
        const val DELAY_PAGE = 3000L
    }
}
