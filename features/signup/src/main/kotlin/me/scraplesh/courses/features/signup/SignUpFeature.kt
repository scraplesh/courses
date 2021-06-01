package me.scraplesh.courses.features.signup

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import me.scraplesh.courses.domain.usecases.SignUpUseCase
import me.scraplesh.courses.features.signup.SignUpFeature.Effect
import me.scraplesh.courses.features.signup.SignUpFeature.Event
import me.scraplesh.courses.features.signup.SignUpFeature.Intention
import me.scraplesh.courses.features.signup.SignUpFeature.State
import me.scraplesh.courses.mvi.Actor
import me.scraplesh.courses.mvi.ActorReducerFeature
import javax.inject.Inject

class SignUpFeature @Inject constructor(signUpUseCase: SignUpUseCase) :
    ActorReducerFeature<Intention, Effect, State, Event>(
        initialState = State.Content(email = "", password = "", name = ""),
        actor = SignUpActor(signUpUseCase = signUpUseCase),
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
                is Effect.NameChanged -> when (state) {
                    is State.Content -> state.copy(name = effect.name)
                    else -> state
                }
                is Effect.SingUpCompleted -> State.Content(
                    email = effect.email,
                    password = effect.password,
                    name = effect.name
                )
                Effect.SignUpFailed -> State.Error
                is Effect.ErrorHided -> State.Content(
                    email = effect.email,
                    password = effect.password,
                    name = effect.name
                )
                Effect.NoEffect -> state
            }
        },
        eventEmitter = { action, effect, _ ->
            when {
                action == Intention.NavigateBack -> Event.NavigatedBack
                effect is Effect.SingUpCompleted -> Event.SingedUp
                else -> null
            }
        }
    ) {

    sealed interface State {
        object Loading : State
        data class Content(val email: String, val password: String, val name: String) : State
        object Error : State
    }

    sealed interface Intention {
        object NavigateBack : Intention
        object SignUp : Intention
        class ChangeEmail(val email: String) : Intention
        class ChangePassword(val password: String) : Intention
        class ChangeName(val name: String) : Intention
    }

    sealed interface Effect {
        object NoEffect : Effect
        object SignUpFailed : Effect
        object LoadingStarted : Effect
        class SingUpCompleted(val email: String, val password: String, val name: String) : Effect
        class EmailChanged(val login: String) : Effect
        class PasswordChanged(val password: String) : Effect
        class NameChanged(val name: String) : Effect
        class ErrorHided(val email: String, val password: String, val name: String) : Effect
    }

    enum class Event { NavigatedBack, SingedUp, }

    class SignUpActor(private val signUpUseCase: SignUpUseCase) : Actor<Intention, State, Effect> {
        override fun invoke(intention: Intention, state: State): Flow<Effect> = when (intention) {
            is Intention.ChangeEmail -> flowOf(Effect.EmailChanged(intention.email))
            is Intention.ChangePassword -> flowOf(Effect.PasswordChanged(intention.password))
            Intention.NavigateBack -> noEffect()
            Intention.SignUp -> {
                if (state is State.Content) {
                    signUp(state.email, state.password, state.name)
                } else {
                    noEffect()
                }
            }
            is Intention.ChangeName -> flowOf(Effect.NameChanged(intention.name))
        }

        private fun noEffect() = flowOf(Effect.NoEffect)

        private fun signUp(email: String, password: String, name: String): Flow<Effect> {
            return signUpUseCase(SignUpUseCase.SignUpArgs(email, password, name))
                .map<Unit, Effect> { Effect.SingUpCompleted(email, password, name) }
                .onStart { emit(Effect.LoadingStarted) }
                .catch {
                    emit(Effect.SignUpFailed)
                    delay(DELAY_ERROR)
                    emit(Effect.ErrorHided(email, password, name))
                }
        }

        private companion object {
            const val DELAY_ERROR = 3000L
        }
    }

}
