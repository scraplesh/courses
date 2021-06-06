package ru.emba.cbs.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import ru.emba.cbs.data.database.dao.CoursesDao
import ru.emba.cbs.data.database.dao.ReviewsDao
import ru.emba.cbs.data.database.entity.CourseEntity
import ru.emba.cbs.data.database.entity.ReviewEntity

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