package ru.emba.cbs.domain.model

interface UserInfo {
    val email: String
    val name: String
    val lastName: String?
    val patronymic: String?
}
