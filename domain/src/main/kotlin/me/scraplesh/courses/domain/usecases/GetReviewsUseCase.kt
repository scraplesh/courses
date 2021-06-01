package me.scraplesh.courses.domain.usecases

import me.scraplesh.courses.domain.model.Review
import me.scraplesh.courses.domain.repo.ReviewsRepository
import javax.inject.Inject

class GetReviewsUseCase @Inject constructor(private val repo: ReviewsRepository) :
    SingleUseCase<EmptyArgs, List<Review>>() {
    override suspend fun single(args: EmptyArgs?): List<Review> = repo.getReviews()
}
