package me.scraplesh.courses.domain.usecases

import me.scraplesh.courses.domain.services.NetworkService
import javax.inject.Inject

class CheckNetworkAvailabilityUseCase @Inject constructor(
    private val networkService: NetworkService
) :
    SingleUseCase<EmptyArgs, Boolean>() {
    override suspend fun single(args: EmptyArgs?): Boolean = networkService.isNetworkAvailable()
}
