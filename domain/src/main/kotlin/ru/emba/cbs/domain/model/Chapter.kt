package ru.emba.cbs.domain.model

interface Chapter {
    val title: String
    val state: ru.emba.cbs.domain.common.ChapterState
    val chapters: List<Chapter>
}