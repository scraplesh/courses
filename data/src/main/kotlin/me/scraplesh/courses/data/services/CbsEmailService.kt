package me.scraplesh.courses.data.services

import android.util.Patterns
import me.scraplesh.courses.domain.services.EmailService
import javax.inject.Inject

class CbsEmailService @Inject constructor() : EmailService {
    override fun isEmailValid(email: String): Boolean =
        Patterns.EMAIL_ADDRESS.matcher(email).matches()
}