package ru.emba.cbs.features.signin

import android.view.View
import android.widget.EditText
import androidx.core.view.isVisible
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.drop
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.filterIsInstance
import kotlinx.coroutines.flow.map
import ru.emba.cbs.common.didSet
import ru.emba.cbs.features.signin.databinding.FragmentSigninBinding
import ru.emba.cbs.mvi.Ui
import ru.ldralighieri.corbind.view.clicks
import ru.ldralighieri.corbind.widget.textChanges
import javax.inject.Inject

class SignInUi @Inject constructor() :
    Ui<SignInUi.Reaction, SignInUi.UiState, FragmentSigninBinding>() {
    sealed interface Reaction {
        object SignInClicked : Reaction
        object RetryClicked : Reaction
        object RestoreAccountClicked : Reaction
        object BackClicked : Reaction
        class EmailChanged(val email: String) : Reaction
        class PasswordChanged(val password: String) : Reaction
    }

    sealed interface UiState {
        class Content(val email: String, val password: String) : UiState
        object Loading : UiState
        class Error(val message: String) : UiState
    }

    private lateinit var rootView: View
    private var snackBar: Snackbar? = null
    private var backButton: View by didSet {
        clicks().react { Reaction.BackClicked }
    }
    private var emailField: EditText by didSet {
        states.filterIsInstance<UiState.Content>()
            .map { it.email }
            .distinctUntilChanged()
            .filter { it != text.toString() }
            .subscribe(::setText)

        textChanges().drop(1)
            .filter { it != (state as? UiState.Content)?.email }
            .react { Reaction.EmailChanged(it.toString()) }
    }
    private var passwordField: EditText by didSet {
        states.filterIsInstance<UiState.Content>()
            .map { it.password }
            .distinctUntilChanged()
            .filter { it != text.toString() }
            .subscribe(::setText)

        textChanges().drop(1)
            .filter { it != (state as? UiState.Content)?.password }
            .react { Reaction.PasswordChanged(it.toString()) }
    }
    private var loginButton: View by didSet {
        clicks().react { Reaction.SignInClicked }
    }
    private var restoreButton: View by didSet {
        clicks().react { Reaction.RestoreAccountClicked }
    }
    private var loadingView: View by didSet {
        states.map { it == UiState.Loading }
            .distinctUntilChanged()
            .subscribe { isVisible = it }
    }

    override fun bindViews(view: FragmentSigninBinding) {
        with(view) {
            rootView = root
            backButton = buttonSigninBack
            emailField = edittextSigninEmail
            passwordField = edittextSigninPassword
            loginButton = buttonSignin
            restoreButton = textviewSigninRestore
            loadingView = progressbarSignin

            configureSnackbar()
        }
    }

    private fun configureSnackbar() {
        states.distinctUntilChanged()
            .subscribe { state ->
                if (state is UiState.Error) {
                    Snackbar.make(rootView, state.message, Snackbar.LENGTH_INDEFINITE)
                        .setAction(rootView.context.getString(R.string.signin_retry)) {
                            react(Reaction.RetryClicked)
                        }
                        .also { snackBar = it }
                        .show()
                } else {
                    snackBar?.dismiss()
                }
            }
    }

}
