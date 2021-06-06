package ru.emba.cbs.data.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "reviews")
data class ReviewEntity(
    @PrimaryKey
    override val id: String,
    override val name: String,
    override val rating: Int,
    override val text: String,
) : ru.emba.cbs.domain.model.Review {
    constructor(other: ru.emba.cbs.domain.model.Review) : this(
        other.id,
        other.name,
        other.rating,
        other.text,
    )
}