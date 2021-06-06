package ru.emba.cbs.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ru.emba.cbs.data.services.AndroidNetworkService
import ru.emba.cbs.data.services.CbsEmailService
import ru.emba.cbs.data.services.CbsAuthService

@Module
@InstallIn(SingletonComponent::class)
abstract class ServiceModule {
    @Binds
    abstract fun bindNetworkService(service: AndroidNetworkService): ru.emba.cbs.domain.services.NetworkService

    @Binds
    abstract fun bindEmailService(service: CbsEmailService): ru.emba.cbs.domain.services.EmailService

    @Binds
    abstract fun bindAuthService(service: CbsAuthService): ru.emba.cbs.domain.services.AuthService
}