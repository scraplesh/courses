package ru.emba.cbs.domain.services

interface EmailService {
    fun isEmailValid(email: String): Boolean
}
