package ru.emba.cbs.domain.usecases

import ru.emba.cbs.domain.services.NetworkService
import javax.inject.Inject

class CheckNetworkAvailabilityUseCase @Inject constructor(
    private val networkService: NetworkService
) :
    SingleUseCase<EmptyArgs, Boolean>() {
    override suspend fun single(args: EmptyArgs?): Boolean = networkService.isNetworkAvailable()
}
