package ru.emba.cbs.data.repo

class RemoteReviewsRepository : ru.emba.cbs.domain.repo.ReviewsRepository {
    override suspend fun getReviews(): List<ru.emba.cbs.domain.model.Review> {
        TODO("Not yet implemented")
    }
}