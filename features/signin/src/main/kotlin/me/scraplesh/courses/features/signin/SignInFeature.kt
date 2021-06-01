package me.scraplesh.courses.features.signin

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import me.scraplesh.courses.domain.usecases.SignInUseCase
import me.scraplesh.courses.features.signin.SignInFeature.Effect
import me.scraplesh.courses.features.signin.SignInFeature.Event
import me.scraplesh.courses.features.signin.SignInFeature.Intention
import me.scraplesh.courses.features.signin.SignInFeature.State
import me.scraplesh.courses.mvi.Actor
import me.scraplesh.courses.mvi.ActorReducerFeature
import javax.inject.Inject

class SignInFeature @Inject constructor(signInUseCase: SignInUseCase) :
    ActorReducerFeature<Intention, Effect, State, Event>(
        initialState = State.Content(email = "", password = ""),
        actor = SignInActor(signInUseCase = signInUseCase),
        reducer = { state, effect ->
            when (effect) {
                Effect.LoadingStarted -> State.Loading
                is Effect.EmailChanged -> {
                    when (state) {
                        is State.Content -> state.copy(email = effect.login)
                        else -> state
                    }
                }
                is Effect.PasswordChanged -> {
                    when (state) {
                        is State.Content -> state.copy(password = effect.password)
                        else -> state
                    }
                }
                is Effect.SingInCompleted -> {
                    State.Content(email = effect.email, password = effect.password)
                }
                Effect.SignInFailed -> State.Error
                is Effect.ErrorHided -> {
                    State.Content(email = effect.email, password = effect.password)
                }
                Effect.NoEffect -> state
            }
        },
        eventEmitter = { action, effect, _ ->
            when {
                action == Intention.NavigateBack -> Event.NavigatedBack
                action == Intention.RestoreAccount -> Event.RestoreAccountRequested
                effect is Effect.SingInCompleted -> Event.SingInCompleted
                else -> null
            }
        }
    ) {

    sealed interface State {
        object Loading : State
        data class Content(val email: String, val password: String) : State
        object Error : State
    }

    sealed interface Intention {
        object NavigateBack : Intention
        object SignIn : Intention
        object RestoreAccount : Intention
        class ChangeEmail(val email: String) : Intention
        class ChangePassword(val password: String) : Intention
    }

    sealed interface Effect {
        object NoEffect : Effect
        object SignInFailed : Effect
        object LoadingStarted : Effect
        class SingInCompleted(val email: String, val password: String) : Effect
        class EmailChanged(val login: String) : Effect
        class PasswordChanged(val password: String) : Effect
        class ErrorHided(val email: String, val password: String) : Effect
    }

    enum class Event { NavigatedBack, RestoreAccountRequested, SingInCompleted, }

    class SignInActor(private val signInUseCase: SignInUseCase) : Actor<Intention, State, Effect> {
        override fun invoke(intention: Intention, state: State): Flow<Effect> = when (intention) {
            is Intention.ChangeEmail -> flowOf(Effect.EmailChanged(intention.email))
            is Intention.ChangePassword -> flowOf(Effect.PasswordChanged(intention.password))
            Intention.NavigateBack -> noEffect()
            Intention.RestoreAccount -> noEffect()
            Intention.SignIn -> {
                if (state is State.Content) {
                    signIn(state.email, state.password)
                } else {
                    noEffect()
                }
            }
        }

        private fun noEffect() = flowOf(Effect.NoEffect)

        private fun signIn(email: String, password: String): Flow<Effect> {
            return signInUseCase(SignInUseCase.SignInArgs(email, password))
                .map<Unit, Effect> { Effect.SingInCompleted(email, password) }
                .onStart { emit(Effect.LoadingStarted) }
                .catch {
                    emit(Effect.SignInFailed)
                    delay(DELAY_ERROR)
                    emit(Effect.ErrorHided(email, password))
                }
        }

        private companion object {
            const val DELAY_ERROR = 3000L
        }
    }

}
