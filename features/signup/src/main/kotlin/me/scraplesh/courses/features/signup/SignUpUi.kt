package me.scraplesh.courses.features.signup

import android.text.Spannable
import android.text.SpannableStringBuilder
import android.text.TextPaint
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.view.View
import android.widget.EditText
import android.widget.TextView
import androidx.core.view.isVisible
import com.google.android.material.textfield.TextInputLayout
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.channelFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.drop
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.filterIsInstance
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.isActive
import me.scraplesh.courses.common.didSet
import me.scraplesh.courses.features.signup.databinding.FragmentSignupBinding
import me.scraplesh.courses.mvi.Ui
import ru.emba.uikit.toolbar.CbsToolbar
import ru.ldralighieri.corbind.view.clicks
import ru.ldralighieri.corbind.view.focusChanges
import ru.ldralighieri.corbind.widget.textChanges
import javax.inject.Inject

class SignUpUi @Inject constructor() :
    Ui<SignUpUi.Reaction, SignUpUi.UiState, FragmentSignupBinding>() {
    sealed interface Reaction {
        object CreateAccountClicked : Reaction
        object BackClicked : Reaction
        object PrivacyPolicyClicked : Reaction
        class EmailFieldFocusLost(val email: String) : Reaction
        class EmailChanged(val email: String) : Reaction
        class NameChanged(val name: String) : Reaction
        class PasswordChanged(val password: String) : Reaction
    }

    sealed interface UiState {
        class Content(val email: String, val name: String, val password: String) : UiState
        object Loading : UiState
        class Error(val errors: List<ErrorType>) : UiState
    }

    private var toolbar: CbsToolbar by didSet {
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

        focusChanges().filter { isFocused -> isFocused.not() && text.isNotEmpty() }
            .react { Reaction.EmailFieldFocusLost(text.toString()) }
    }
    private var nameInputLayout: TextInputLayout by didSet {
        states.filterIsInstance<UiState.Error>()
            .filter { state -> ErrorType.EmptyName in state.errors }
            .subscribe { error = context.getString(R.string.signup_empty_name) }

        states.filter { it !is UiState.Error }
            .distinctUntilChanged()
            .subscribe { error = null }
    }
    private var emailInputLayout: TextInputLayout by didSet {
        states.filterIsInstance<UiState.Error>()
            .filter { state -> ErrorType.InvalidEmail in state.errors }
            .subscribe { error = context.getString(R.string.signup_invalid_email) }

        states.filter { it !is UiState.Error }
            .distinctUntilChanged()
            .subscribe { error = null }
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
    private var privacyPolicyTextView: TextView by didSet {
        movementMethod = LinkMovementMethod.getInstance()
        text =
            SpannableStringBuilder(context.getString(R.string.signup_privacy_policy_part1)).apply {
                append(' ')
                append(
                    context.getString(R.string.signup_privacy_policy_part2),
                    object : ClickableSpan() {
                        override fun onClick(widget: View) {
                            react(Reaction.PrivacyPolicyClicked)
                        }

                        override fun updateDrawState(ds: TextPaint) {
                            ds.isUnderlineText = true
                        }
                    },
                    Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
                )
                append('.')
            }
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
            toolbar = cbstoolbarSignup
            createAccountButton = buttonSignupCreateAccount
            nameInputLayout = textinputlayoutSignupName
            emailInputLayout = textinputlayoutSignupEmail
            emailField = textinpitedittextSignupEmail
            nameField = textinpitedittextSignupName
            passwordField = textinpitedittextSignupPassword
            privacyPolicyTextView = textviewSignupPrivacyPolicy
            loadingView = progressbarSignup
        }
    }

    private fun CbsToolbar.clicks(): Flow<Unit> = channelFlow {
        setOnClickListener { if (isActive) trySend(Unit) }
        awaitClose { setOnClickListener(null) }
    }
}
