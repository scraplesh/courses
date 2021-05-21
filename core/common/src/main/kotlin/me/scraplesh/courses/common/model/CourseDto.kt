package me.scraplesh.courses.common.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import me.scraplesh.courses.domain.model.Course

@Parcelize
class CourseDto(
    override val id: String,
    override val title: String,
    override val progress: Int,
    override val url: String,
    override val chapters: List<ChapterDto>
) : Course, Parcelable {
    constructor(other: Course) : this(
        id = other.id,
        title = other.title,
        progress = other.progress,
        url = other.url,
        chapters = other.chapters.map { ChapterDto(it) }
    )
}
