package me.scraplesh.courses.features.settings

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import me.scraplesh.courses.domain.model.Course
import me.scraplesh.courses.domain.model.UserInfo
import me.scraplesh.courses.domain.usecases.SignOutUseCase
import me.scraplesh.courses.domain.usecases.UpdateUserUseCase
import me.scraplesh.courses.features.settings.SettingsViewModel.Effect
import me.scraplesh.courses.features.settings.SettingsViewModel.Event
import me.scraplesh.courses.features.settings.SettingsViewModel.Intention
import me.scraplesh.courses.features.settings.SettingsViewModel.State
import me.scraplesh.courses.mvi.Actor
import me.scraplesh.courses.mvi.ActorReducerViewModel

class SettingsViewModel(
    user: UserInfo,
    updateUserUseCase: UpdateUserUseCase,
    signOutUseCase: SignOutUseCase
) : ActorReducerViewModel<Intention, Effect, State, Event>(
    initialState = State.Content(
        email = user.email,
        name = user.name,
        lastName = user.lastName ?: "",
        patronymic = user.patronymic ?: ""
    ),
    actor = SettingsActor(updateUserUseCase = updateUserUseCase, signOutUseCase = signOutUseCase),
    reducer = { state, effect ->
        when (effect) {
            Effect.LoadingStarted -> State.Loading
            is Effect.EmailChanged -> {
                when (state) {
                    is State.Content -> state.copy(email = effect.email)
                    else -> state
                }
            }
            is Effect.NameChanged -> {
                when (state) {
                    is State.Content -> state.copy(name = effect.name)
                    else -> state
                }
            }
            is Effect.LastNameChanged -> when (state) {
                is State.Content -> state.copy(lastName = effect.lastName)
                else -> state
            }
            is Effect.PatronymicChanged -> when (state) {
                is State.Content -> state.copy(patronymic = effect.patronymic)
                else -> state
            }
            is Effect.UpdateUserCompleted -> State.Content(
                email = effect.email,
                name = effect.name,
                lastName = effect.lastName,
                patronymic = effect.patronymic
            )
            Effect.UpdateUserFailed -> State.Error
            is Effect.ErrorHided -> State.Content(
                email = effect.email,
                name = effect.name,
                lastName = effect.lastName,
                patronymic = effect.patronymic
            )
            Effect.NoEffect -> state
            Effect.SignedOut -> state
        }
    },
    eventEmitter = { action, effect, _ ->
        when {
            action == Intention.NavigateBack -> Event.NavigatedBack
            action == Intention.ChangePassword -> Event.ChangePasswordRequested
            action == Intention.ShowContacts -> Event.ShowContactsRequested
            effect == Effect.SignedOut -> Event.SignedOut
            else -> null
        }
    }
) {

    sealed interface State {
        object Loading : State
        data class Content(
            val email: String,
            val name: String,
            val lastName: String,
            val patronymic: String
        ) : State
        object Error : State
    }

    sealed interface Intention {
        object SignOut : Intention
        object NavigateBack : Intention
        object UpdateUser : Intention
        object ChangePassword : Intention
        object ShowContacts : Intention
        class ChangeEmail(val email: String) : Intention
        class ChangeName(val name: String) : Intention
        class ChangeLastName(val lastName: String) : Intention
        class ChangePatronymic(val patronymic: String) : Intention
    }

    sealed interface Effect {
        object NoEffect : Effect
        object UpdateUserFailed : Effect
        object LoadingStarted : Effect
        object SignedOut : Effect

        class UpdateUserCompleted(
            val email: String,
            val name: String,
            val lastName: String,
            val patronymic: String
        ) : Effect

        class EmailChanged(val email: String) : Effect
        class NameChanged(val name: String) : Effect
        class LastNameChanged(val lastName: String) : Effect
        class PatronymicChanged(val patronymic: String) : Effect

        class ErrorHided(
            val email: String,
            val name: String,
            val lastName: String,
            val patronymic: String
        ) : Effect
    }

    enum class Event { NavigatedBack, ChangePasswordRequested, ShowContactsRequested, SignedOut, }

    class SettingsActor(
        private val updateUserUseCase: UpdateUserUseCase,
        private val signOutUseCase: SignOutUseCase
    ) :
        Actor<Intention, State, Effect> {
        override fun invoke(intention: Intention, state: State): Flow<Effect> = when (intention) {
            is Intention.ChangeEmail -> flowOf(Effect.EmailChanged(intention.email))
            is Intention.ChangeName -> flowOf(Effect.NameChanged(intention.name))
            Intention.NavigateBack -> noEffect()
            Intention.ChangePassword -> noEffect()
            Intention.UpdateUser -> {
                if (state is State.Content) updateUser(state)
                else noEffect()
            }
            is Intention.ChangeLastName -> {
                flowOf(Effect.LastNameChanged(intention.lastName))
            }
            is Intention.ChangePatronymic -> {
                flowOf(Effect.PatronymicChanged(intention.patronymic))
            }
            Intention.ShowContacts -> noEffect()
            Intention.SignOut -> signOut()
        }

        private fun signOut(): Flow<Effect> {
            return flow {
                signOutUseCase()
                emit(Effect.SignedOut)
            }
        }

        private fun noEffect() = flowOf(Effect.NoEffect)

        private fun updateUser(state: State.Content): Flow<Effect> {
            return flow {
                with(state) {
                    try {
                        emit(Effect.LoadingStarted)
                        updateUserUseCase(
                            UpdateUserUseCase.UpdateUserArgs(email, name, lastName, patronymic)
                        )
                        emit(Effect.UpdateUserCompleted(email, name, lastName, patronymic))
                    } catch (e: Throwable) {
                        emit(Effect.UpdateUserFailed)
                        delay(DELAY_ERROR)
                        emit(Effect.ErrorHided(email, name, lastName, patronymic))
                    }
                }
            }
        }

        private companion object {
            const val DELAY_ERROR = 3000L
        }
    }

    class Factory @AssistedInject constructor(
        @Assisted private val course: Course,
        private val updateUserUseCase: UpdateUserUseCase,
        private val signOutUseCase: SignOutUseCase
    ) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return modelClass.getConstructor(
                UserInfo::class.java,
                UpdateUserUseCase::class.java,
                SignOutUseCase::class.java
            )
                .newInstance(course, updateUserUseCase, signOutUseCase)
        }
    }

    @dagger.assisted.AssistedFactory
    interface AssistedFactory {
        fun create(course: Course): Factory
    }
}
