package me.scraplesh.courses.domain.usecases

import me.scraplesh.courses.domain.services.NetworkService
import javax.inject.Inject

class CheckNetworkAvailabilityUseCase @Inject constructor(
    private val networkService: NetworkService
) :
    BaseUseCase<EmptyArgs, Boolean>() {
    override suspend fun invoke(args: EmptyArgs?): Boolean = networkService.isNetworkAvailable()
}
