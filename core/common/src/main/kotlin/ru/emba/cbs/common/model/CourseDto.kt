package ru.emba.cbs.common.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class CourseDto(
    override val id: String,
    override val title: String,
    override val progress: Int,
    override val url: String,
    override val chapters: List<ChapterDto>
) : ru.emba.cbs.domain.model.Course, Parcelable {
    constructor(other: ru.emba.cbs.domain.model.Course) : this(
        id = other.id,
        title = other.title,
        progress = other.progress,
        url = other.url,
        chapters = other.chapters.map { ChapterDto(it) }
    )
}
