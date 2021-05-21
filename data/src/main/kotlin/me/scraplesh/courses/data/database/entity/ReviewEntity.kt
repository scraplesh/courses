package me.scraplesh.courses.data.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import me.scraplesh.courses.domain.model.Review

@Entity(tableName = "reviews")
data class ReviewEntity(
    @PrimaryKey
    override val id: String,
    override val name: String,
    override val rating: Int,
    override val text: String,
) : Review {
    constructor(other: Review) : this(
        other.id,
        other.name,
        other.rating,
        other.text,
    )
}