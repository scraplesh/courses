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
import javax.inject.Inject

class SignUpUi @Inject constructor() :
    Ui<SignUpUi.Reaction, SignUpUi.UiState, FragmentSignupBinding>() {
    sealed interface Reaction {
        object CreateAccountClicked : Reaction
        object BackClicked : Reaction
        class EmailChanged(val email: String) : Reaction
        class NameChanged(val name: String) : Reaction
        class PasswordChanged(val password: String) : Reaction
    }

    sealed interface UiState {
        class Content(val email: String, val name: String, val password: String) : UiState
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
    private var passwordField: EditText by didSet {
        states.filterIsInstance<UiState.Content>()
            .map { it.password }
            .filter { it != text.toString() }
            .subscribe(::setText)

        textChanges().drop(1)
            .filter { it != (state as? UiState.Content)?.password }
            .react { Reaction.PasswordChanged(it.toString()) }
    }
    private var createAccountButton: View by didSet {
        clicks().react { Reaction.CreateAccountClicked }
    }
    private var loadingView: View by didSet {
        states.map { it == UiState.Loading }
            .distinctUntilChanged()
            .subscribe { isVisible = it }
    }

    override fun bindViews(view: FragmentSignupBinding) {
        with(view) {
            backButton = buttonSignupBack
            createAccountButton = buttonSignupSignup
            emailField = edittextSignupEmail
            nameField = edittextSignupName
            passwordField = edittextSignupPassword
            loadingView = progressbarSignup
        }
    }

}
