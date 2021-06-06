package ru.emba.cbs.domain.model

interface Course {
    val id: String
    val title: String
    val progress: Int
    val url: String
    val chapters: List<Chapter>
}
