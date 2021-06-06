package ru.emba.cbs.domain.usecases

import javax.inject.Inject

class GetReviewsUseCase @Inject constructor(private val repo: ru.emba.cbs.domain.repo.ReviewsRepository) :
    SingleUseCase<EmptyArgs, List<ru.emba.cbs.domain.model.Review>>() {
    override suspend fun single(args: EmptyArgs?): List<ru.emba.cbs.domain.model.Review> = repo.getReviews()
}
