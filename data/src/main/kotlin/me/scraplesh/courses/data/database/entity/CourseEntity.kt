package me.scraplesh.courses.data.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import me.scraplesh.courses.data.database.ChaptersConverter
import me.scraplesh.courses.data.model.ChapterData
import me.scraplesh.courses.domain.model.Course

@Entity(tableName = "courses")
@TypeConverters(ChaptersConverter::class)
data class CourseEntity(
    @PrimaryKey
    override val id: String,
    override val title: String,
    override val progress: Int,
    override val url: String,
    override val chapters: List<ChapterData>
) : Course {
    constructor(other: Course) : this(
        id = other.id,
        title = other.title,
        progress = other.progress,
        url = other.url,
        chapters = other.chapters.map { ChapterData(it) },
    )
}