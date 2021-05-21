package me.scraplesh.courses.domain.model

interface Course {
    val id: String
    val title: String
    val progress: Int
    val url: String
    val chapters: List<Chapter>
}
