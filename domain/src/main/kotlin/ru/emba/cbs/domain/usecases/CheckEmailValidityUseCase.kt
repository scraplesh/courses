package ru.emba.cbs.domain.usecases

import ru.emba.cbs.domain.services.EmailService
import javax.inject.Inject

class CheckEmailValidityUseCase @Inject constructor(private val service: EmailService) :
    SingleUseCase<CheckEmailValidityUseCase.Args, Boolean>() {

    @JvmInline
    value class Args(val email: String) : ru.emba.cbs.domain.usecases.Args

    override suspend fun single(args: Args?): Boolean {
        requireNotNull(args)
        return service.isEmailValid(args.email)
    }

}
