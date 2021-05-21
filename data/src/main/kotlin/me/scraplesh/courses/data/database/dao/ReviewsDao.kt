package me.scraplesh.courses.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import me.scraplesh.courses.data.database.entity.ReviewEntity

@Dao
interface ReviewsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertReviews(courses: List<ReviewEntity>)

    @Query("SELECT * FROM reviews")
    fun getReviews(): Flow<List<ReviewEntity>>

    @Query("DELETE FROM reviews")
    fun clear()
}
