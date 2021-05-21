package me.scraplesh.courses.data.repo

import me.scraplesh.courses.domain.model.Review
import me.scraplesh.courses.domain.repo.ReviewsRepository

class RemoteReviewsRepository : ReviewsRepository {
    override suspend fun getReviews(): List<Review> {
        TODO("Not yet implemented")
    }
}