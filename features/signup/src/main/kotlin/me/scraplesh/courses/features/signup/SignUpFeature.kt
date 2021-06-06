package me.scraplesh.courses.features.signup

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import me.scraplesh.courses.domain.usecases.CheckEmailValidityUseCase
import me.scraplesh.courses.domain.usecases.SignUpUseCase
import me.scraplesh.courses.features.signup.SignUpFeature.Effect
import me.scraplesh.courses.features.signup.SignUpFeature.Event
import me.scraplesh.courses.features.signup.SignUpFeature.Intention
import me.scraplesh.courses.features.signup.SignUpFeature.State
import me.scraplesh.courses.mvi.Actor
import me.scraplesh.courses.mvi.ActorReducerFeature
import javax.inject.Inject

class SignUpFeature @Inject constructor(
    signUpUseCase: SignUpUseCase,
    checkEmailValidityUseCase: CheckEmailValidityUseCase
) :
    ActorReducerFeature<Intention, Effect, State, Event>(
        initialState = State(),
        actor = SignUpActor(
            signUpUseCase = signUpUseCase,
            checkEmailValidityUseCase = checkEmailValidityUseCase
        ),
        reducer = { state, effect ->
            when (effect) {
                Effect.LoadingStarted -> state.copy(isLoading = true)
                is Effect.EmailChanged -> state.copy(email = effect.login)
                is Effect.PasswordChanged -> state.copy(password = effect.password)
                is Effect.NameChanged -> state.copy(name = effect.name)
                is Effect.SingUpCompleted -> state.copy(isLoading = false)
                Effect.SignUpFailed ->
                    state.copy(isLoading = false, errors = listOf(ErrorType.SignUpFailed))
                is Effect.ErrorHided -> state.copy(errors = emptyList())
                Effect.NoEffect -> state
                Effect.EmailInvalid -> state.copy(errors = listOf(ErrorType.InvalidEmail))
            }
        },
        eventEmitter = { action, effect, _ ->
            when {
                action == Intention.NavigateBack -> Event.NavigatedBack
                action == Intention.ShowPrivacyPolicy -> Event.PrivacyPolicyRequested
                effect is Effect.SingUpCompleted -> Event.SingedUp
                else -> null
            }
        }
    ) {

    data class State(
        val email: String = "",
        val password: String = "",
        val name: String = "",
        val isLoading: Boolean = false,
        val errors: List<ErrorType> = emptyList()
    )

    sealed interface Intention {
        object NavigateBack : Intention
        object SignUp : Intention
        object ShowPrivacyPolicy : Intention
        class CheckEmailValidity(val email: String) : Intention
        class ChangeEmail(val email: String) : Intention
        class ChangePassword(val password: String) : Intention
        class ChangeName(val name: String) : Intention
    }

    sealed interface Effect {
        object NoEffect : Effect
        object EmailInvalid : Effect
        object SignUpFailed : Effect
        object LoadingStarted : Effect
        object SingUpCompleted : Effect
        class EmailChanged(val login: String) : Effect
        class PasswordChanged(val password: String) : Effect
        class NameChanged(val name: String) : Effect
        object ErrorHided : Effect
    }

    enum class Event { NavigatedBack, SingedUp, PrivacyPolicyRequested, }

    class SignUpActor(
        private val signUpUseCase: SignUpUseCase,
        private val checkEmailValidityUseCase: CheckEmailValidityUseCase,
    ) : Actor<Intention, State, Effect> {
        override fun invoke(intention: Intention, state: State): Flow<Effect> = when (intention) {
            is Intention.ChangeEmail -> flowOf(Effect.EmailChanged(intention.email))
            is Intention.ChangePassword -> flowOf(Effect.PasswordChanged(intention.password))
            Intention.NavigateBack -> noEffect()
            Intention.SignUp -> signUp(state.email, state.password, state.name)
            is Intention.ChangeName -> flowOf(Effect.NameChanged(intention.name))
            Intention.ShowPrivacyPolicy -> noEffect()
            is Intention.CheckEmailValidity -> checkEmailValidity(intention.email)
        }

        private fun checkEmailValidity(email: String): Flow<Effect> {
            return checkEmailValidityUseCase(CheckEmailValidityUseCase.Args(email)).map { isEmailValid ->
                if (isEmailValid) Effect.NoEffect
                else Effect.EmailInvalid
            }
        }

        private fun noEffect() = flowOf(Effect.NoEffect)

        private fun signUp(email: String, password: String, name: String): Flow<Effect> {
            return signUpUseCase(SignUpUseCase.SignUpArgs(email, password, name))
                .map<Unit, Effect> { Effect.SingUpCompleted }
                .onStart { emit(Effect.LoadingStarted) }
                .catch {
                    emit(Effect.SignUpFailed)
                    delay(DELAY_ERROR)
                    emit(Effect.ErrorHided)
                }
        }

        private companion object {
            const val DELAY_ERROR = 3000L
        }
    }

}
