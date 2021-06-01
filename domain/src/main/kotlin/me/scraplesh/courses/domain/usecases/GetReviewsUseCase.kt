package me.scraplesh.courses.domain.usecases

import me.scraplesh.courses.domain.model.Review
import me.scraplesh.courses.domain.repo.ReviewsRepository

class GetReviewsUseCase(private val repo: ReviewsRepository) :
    SingleUseCase<EmptyArgs, List<Review>>() {
    override suspend fun invoke(args: EmptyArgs?): List<Review> = repo.getReviews()
}
