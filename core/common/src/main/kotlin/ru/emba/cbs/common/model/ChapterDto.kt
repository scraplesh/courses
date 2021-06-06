package ru.emba.cbs.common.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class ChapterDto(
    override val title: String,
    override val state: ru.emba.cbs.domain.common.ChapterState,
    override val chapters: List<ChapterDto>
) : ru.emba.cbs.domain.model.Chapter, Parcelable {
    constructor(other: ru.emba.cbs.domain.model.Chapter) : this(
        title = other.title,
        state = other.state,
        chapters = other.chapters.map { ChapterDto(it) }
    )
}