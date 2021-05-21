package me.scraplesh.courses.features.signup

import android.view.View
import android.widget.EditText
import androidx.core.view.isVisible
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.drop
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.filterIsInstance
import kotlinx.coroutines.flow.map
import me.scraplesh.courses.common.didSet
import me.scraplesh.courses.features.signup.databinding.FragmentSignupBinding
import me.scraplesh.courses.mvi.Ui
import ru.ldralighieri.corbind.view.clicks
import ru.ldralighieri.corbind.widget.textChanges

class SignUpUi : Ui<SignUpUi.Reaction, SignUpUi.UiState, FragmentSignupBinding>() {
    sealed interface Reaction {
        object SaveClicked : Reaction
        object ChangePasswordClicked : Reaction
        object BackClicked : Reaction
        object SignOutClicked : Reaction
        class EmailChanged(val email: String) : Reaction
        class NameChanged(val name: String) : Reaction
        class LastNameChanged(val lastName: String) : Reaction
        class PatronymicChanged(val patronymic: String) : Reaction
    }

    sealed interface UiState {
        class Content(
            val email: String,
            val name: String,
            val lastName: String,
            val patronymic: String
        ) : UiState

        object Loading : UiState
        class Error(val message: String) : UiState
    }

    private var backButton: View by didSet {
        clicks().react { Reaction.BackClicked }
    }
    private var emailField: EditText by didSet {
        states.filterIsInstance<UiState.Content>()
            .map { it.email }
            .filter { it != text.toString() }
            .subscribe(::setText)

        textChanges().drop(1)
            .filter { it != (state as? UiState.Content)?.email }
            .react { Reaction.EmailChanged(it.toString()) }
    }
    private var nameField: EditText by didSet {
        states.filterIsInstance<UiState.Content>()
            .map { it.name }
            .filter { it != text.toString() }
            .subscribe(::setText)

        textChanges().drop(1)
            .filter { it != (state as? UiState.Content)?.name }
            .react { Reaction.NameChanged(it.toString()) }
    }
    private var lastNameField: EditText by didSet {
        states.filterIsInstance<UiState.Content>()
            .map { it.lastName }
            .filter { it != text.toString() }
            .subscribe(::setText)

        textChanges().drop(1)
            .filter { it != (state as? UiState.Content)?.lastName }
            .react { Reaction.LastNameChanged(it.toString()) }
    }
    private var patronymicField: EditText by didSet {
        states.filterIsInstance<UiState.Content>()
            .map { it.patronymic }
            .filter { it != text.toString() }
            .subscribe(::setText)

        textChanges().drop(1)
            .filter { it != (state as? UiState.Content)?.patronymic }
            .react { Reaction.PatronymicChanged(it.toString()) }
    }
    private var saveButton: View by didSet {
        clicks().react { Reaction.SaveClicked }
    }
    private var logoutButton: View by didSet {
        clicks().react { Reaction.SignOutClicked }
    }
    private var changePasswordButton: View by didSet {
        clicks().react { Reaction.ChangePasswordClicked }
    }
    private var loadingView: View by didSet {
        states.map { it == UiState.Loading }
            .distinctUntilChanged()
            .subscribe { isVisible = it }
    }

    override fun bindViews(view: FragmentSignupBinding) {
        with(view) {
        }
    }

}
