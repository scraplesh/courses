package me.scraplesh.courses.domain.model

import me.scraplesh.courses.domain.common.ChapterState

interface Chapter {
    val title: String
    val state: ChapterState
    val chapters: List<Chapter>
}