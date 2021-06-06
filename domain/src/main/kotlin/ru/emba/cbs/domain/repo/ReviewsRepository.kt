package ru.emba.cbs.domain.repo

interface ReviewsRepository {
    suspend fun getReviews(): List<ru.emba.cbs.domain.model.Review>
}
