package me.scraplesh.courses.domain.usecases

import me.scraplesh.courses.domain.services.NetworkService

class CheckNetworkAvailabilityUseCase(private val networkService: NetworkService) :
    BaseUseCase<EmptyArgs, Boolean>() {
    override suspend fun invoke(args: EmptyArgs?): Boolean = networkService.isNetworkAvailable()
}
