package ru.emba.cbs.domain.model

interface Review {
    val id: String
    val name: String
    val rating: Int
    val text: String
}
