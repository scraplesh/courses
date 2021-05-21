package me.scraplesh.courses.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import me.scraplesh.courses.data.database.dao.CoursesDao
import me.scraplesh.courses.data.database.dao.ReviewsDao
import me.scraplesh.courses.data.database.entity.CourseEntity
import me.scraplesh.courses.data.database.entity.ReviewEntity

@Database(
    entities = [
        CourseEntity::class,
        ReviewEntity::class,
    ],
    version = 1,
    exportSchema = false
)
@TypeConverters(ChaptersConverter::class)
abstract class CoursesDatabase : RoomDatabase() {
    abstract fun coursesDao(): CoursesDao
    abstract fun reviewsDao(): ReviewsDao
}