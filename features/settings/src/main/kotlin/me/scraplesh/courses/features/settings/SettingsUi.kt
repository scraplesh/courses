package me.scraplesh.courses.features.settings

import android.view.View
import android.widget.EditText
import kotlinx.coroutines.flow.drop
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.map
import me.scraplesh.courses.common.didSet
import me.scraplesh.courses.features.settings.databinding.FragmentSettingsBinding
import me.scraplesh.courses.mvi.Ui
import ru.ldralighieri.corbind.view.clicks
import ru.ldralighieri.corbind.widget.textChanges

class SettingsUi : Ui<SettingsUi.Reaction, SettingsUi.UiState, FragmentSettingsBinding>() {

    sealed interface Reaction {
        object SaveClicked : Reaction
        object RestoreAccountClicked : Reaction
        object BackClicked : Reaction
        class LoginChanged(val login: String) : Reaction
        class PasswordChanged(val password: String) : Reaction
    }

    data class UiState(val login: String, val password: String)

    private var backButton: View by didSet {
        clicks().react { Reaction.BackClicked }
    }
    private var loginField: EditText by didSet {
        states.map { it.login }
            .filter { it != text.toString() }
            .subscribe(::setText)

        textChanges().drop(1)
            .filter { it != state?.login }
            .react { Reaction.LoginChanged(it.toString()) }
    }
    private var passwordField: EditText by didSet {
        states.map { it.password }
            .filter { it != text.toString() }
            .subscribe(::setText)

        textChanges().drop(1)
            .filter { it != state?.password }
            .react { Reaction.PasswordChanged(it.toString()) }
    }
    private var loginButton: View by didSet {
        clicks().react { Reaction.SaveClicked }
    }
    private var restoreButton: View by didSet {
        clicks().react { Reaction.RestoreAccountClicked }
    }

    override fun bindViews(view: FragmentSettingsBinding) {
        with(view) {
//            backButton = buttonLoginBack
//            loginField = edittextLoginLogin
//            passwordField = edittextLoginPassword
//            loginButton = buttonLoginLogin
//            restoreButton = textviewLoginRestore
        }
    }

}
