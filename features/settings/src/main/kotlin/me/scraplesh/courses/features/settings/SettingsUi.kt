package me.scraplesh.courses.features.settings

import android.view.View
import android.widget.EditText
import kotlinx.coroutines.flow.drop
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.filterIsInstance
import kotlinx.coroutines.flow.map
import me.scraplesh.courses.common.didSet
import me.scraplesh.courses.features.settings.databinding.FragmentSettingsBinding
import me.scraplesh.courses.mvi.Ui
import ru.ldralighieri.corbind.view.clicks
import ru.ldralighieri.corbind.widget.textChanges
import javax.inject.Inject

class SettingsUi @Inject constructor() :
    Ui<SettingsUi.Reaction, SettingsUi.UiState, FragmentSettingsBinding>() {

    sealed interface Reaction {
        object ContactsClicked : Reaction
        object SaveClicked : Reaction
        object SignOutClicked : Reaction
        object ChangePasswordClicked : Reaction
        object BackClicked : Reaction
        class EmailChanged(val email: String) : Reaction
        class NameChanged(val name: String) : Reaction
        class LastNameChanged(val lastName: String) : Reaction
        class PatronymicChanged(val patronymic: String) : Reaction
    }

    sealed interface UiState {
        object Loading : UiState
        data class Content(
            val email: String,
            val name: String,
            val lastName: String,
            val patronymic: String
        ) : UiState

        object Error : UiState
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
    private var contactsButton: View by didSet {
        clicks().react { Reaction.ContactsClicked }
    }
    private var changePasswordButton: View by didSet {
        clicks().react { Reaction.ChangePasswordClicked }
    }
    private var saveButton: View by didSet {
        clicks().react { Reaction.SaveClicked }
    }
    private var signOutButton: View by didSet {
        clicks().react { Reaction.SignOutClicked }
    }

    override fun bindViews(view: FragmentSettingsBinding) {
        with(view) {
            backButton = buttonSettingsBack
            changePasswordButton = buttonSettingsChangePassword
            saveButton = buttonSettingsSave
            contactsButton = textviewSettingsContacts
            signOutButton = buttonSettingsSignOut
            emailField = edittextSettingsEmail
            nameField = edittextSettingsName
            lastNameField = edittextSettingsLastName
            patronymicField = edittextSettingsPatronymic
        }
    }

}
