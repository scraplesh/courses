package ru.emba.cbs.data.model

class ChapterData(
    override val title: String,
    override val state: ru.emba.cbs.domain.common.ChapterState,
    override val chapters: List<ru.emba.cbs.domain.model.Chapter>,
) : ru.emba.cbs.domain.model.Chapter {
    constructor(other: ru.emba.cbs.domain.model.Chapter) : this(
        title = other.title,
        state = other.state,
        chapters = other.chapters.map { ChapterData(it) }
    )
}