package me.scraplesh.courses.data.model

import me.scraplesh.courses.domain.common.ChapterState
import me.scraplesh.courses.domain.model.Chapter

class ChapterData(
    override val title: String,
    override val state: ChapterState,
    override val chapters: List<Chapter>,
) : Chapter {
    constructor(other: Chapter) : this(
        title = other.title,
        state = other.state,
        chapters = other.chapters.map { ChapterData(it) }
    )
}