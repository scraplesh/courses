package ru.emba.cbs.data.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import ru.emba.cbs.data.database.ChaptersConverter
import ru.emba.cbs.data.model.ChapterData

@Entity(tableName = "courses")
@TypeConverters(ChaptersConverter::class)
data class CourseEntity(
    @PrimaryKey
    override val id: String,
    override val title: String,
    override val progress: Int,
    override val url: String,
    override val chapters: List<ChapterData>
) : ru.emba.cbs.domain.model.Course {
    constructor(other: ru.emba.cbs.domain.model.Course) : this(
        id = other.id,
        title = other.title,
        progress = other.progress,
        url = other.url,
        chapters = other.chapters.map { ChapterData(it) },
    )
}