package me.scraplesh.courses.domain.services

interface EmailService {
    fun isEmailValid(email: String): Boolean
}
