package me.scraplesh.courses.features.settings

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import me.scraplesh.courses.domain.model.UserInfo
import me.scraplesh.courses.domain.usecases.GetUserInfoUseCase
import me.scraplesh.courses.domain.usecases.SignOutUseCase
import me.scraplesh.courses.domain.usecases.UpdateUserUseCase
import me.scraplesh.courses.features.settings.SettingsFeature.Effect
import me.scraplesh.courses.features.settings.SettingsFeature.Event
import me.scraplesh.courses.features.settings.SettingsFeature.Intention
import me.scraplesh.courses.features.settings.SettingsFeature.State
import me.scraplesh.courses.mvi.Actor
import me.scraplesh.courses.mvi.ActorReducerFeature
import javax.inject.Inject

class SettingsFeature @Inject constructor(
    getUserInfoUseCase: GetUserInfoUseCase,
    updateUserUseCase: UpdateUserUseCase,
    signOutUseCase: SignOutUseCase
) : ActorReducerFeature<Intention, Effect, State, Event>(
    initialState = State.Loading,
    bootstrapper = { flowOf(Intention.GetUserInfo) },
    actor = SettingsActor(
        getUserInfoUseCase = getUserInfoUseCase,
        updateUserUseCase = updateUserUseCase,
        signOutUseCase = signOutUseCase
    ),
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
            is Effect.UpdateUserCompleted -> effect.state
            Effect.UpdateUserFailed -> State.Error
            is Effect.ErrorHided -> effect.state
            Effect.NoEffect -> state
            Effect.SignedOut -> state
            Effect.ErrorGettingUserInfo -> State.Error
            is Effect.UserInfoGot -> State.Content(
                email = effect.userInfo.email,
                name = effect.userInfo.name,
                lastName = effect.userInfo.lastName,
                patronymic = effect.userInfo.patronymic
            )
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
            val lastName: String?,
            val patronymic: String?
        ) : State

        object Error : State
    }

    sealed interface Intention {
        object GetUserInfo : Intention

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
        object ErrorGettingUserInfo : Effect
        class UpdateUserCompleted(val state: State.Content) : Effect
        class EmailChanged(val email: String) : Effect
        class NameChanged(val name: String) : Effect
        class LastNameChanged(val lastName: String) : Effect
        class PatronymicChanged(val patronymic: String) : Effect
        class ErrorHided(val state: State.Content) : Effect
        class UserInfoGot(val userInfo: UserInfo) : Effect
    }

    enum class Event { NavigatedBack, ChangePasswordRequested, ShowContactsRequested, SignedOut, }

    class SettingsActor(
        private val getUserInfoUseCase: GetUserInfoUseCase,
        private val updateUserUseCase: UpdateUserUseCase,
        private val signOutUseCase: SignOutUseCase
    ) :
        Actor<Intention, State, Effect> {
        override fun invoke(intention: Intention, state: State): Flow<Effect> = when (intention) {
            Intention.GetUserInfo -> getUserInfo()
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

        private fun getUserInfo(): Flow<Effect> {
            return getUserInfoUseCase().map<UserInfo, Effect> { Effect.UserInfoGot(it) }
                .catch { emit(Effect.ErrorGettingUserInfo) }
        }

        private fun signOut(): Flow<Effect> = signOutUseCase().map { Effect.SignedOut }

        private fun noEffect() = flowOf(Effect.NoEffect)

        private fun updateUser(state: State.Content): Flow<Effect> {
            return state.run {
                updateUserUseCase(
                    UpdateUserUseCase.UpdateUserArgs(email, name, lastName, patronymic)
                )
                    .map<Unit, Effect> { Effect.UpdateUserCompleted(state) }
                    .onStart { emit(Effect.LoadingStarted) }
                    .catch {
                        emit(Effect.UpdateUserFailed)
                        delay(DELAY_ERROR)
                        emit(Effect.ErrorHided(state))
                    }
            }
        }

        private companion object {
            const val DELAY_ERROR = 3000L
        }
    }
}
