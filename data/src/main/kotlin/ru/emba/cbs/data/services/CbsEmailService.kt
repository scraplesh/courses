package ru.emba.cbs.data.services

import android.util.Patterns
import javax.inject.Inject

class CbsEmailService @Inject constructor() : ru.emba.cbs.domain.services.EmailService {
    override fun isEmailValid(email: String): Boolean =
        Patterns.EMAIL_ADDRESS.matcher(email).matches()
}