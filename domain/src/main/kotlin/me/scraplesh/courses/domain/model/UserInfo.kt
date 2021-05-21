package me.scraplesh.courses.domain.model

interface UserInfo {
    val email: String
    val name: String
    val lastName: String?
    val patronymic: String?
}
