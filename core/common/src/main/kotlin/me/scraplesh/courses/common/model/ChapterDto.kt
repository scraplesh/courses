package me.scraplesh.courses.common.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import me.scraplesh.courses.domain.common.ChapterState
import me.scraplesh.courses.domain.model.Chapter

@Parcelize
class ChapterDto(
    override val title: String,
    override val state: ChapterState,
    override val chapters: List<ChapterDto>
) : Chapter, Parcelable {
    constructor(other: Chapter) : this(
        title = other.title,
        state = other.state,
        chapters = other.chapters.map { ChapterDto(it) }
    )
}