package me.scraplesh.courses.domain.repo

import me.scraplesh.courses.domain.model.Review

interface ReviewsRepository {
    suspend fun getReviews(): List<Review>
}
