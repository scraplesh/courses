package me.scraplesh.courses.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import me.scraplesh.courses.data.services.AndroidNetworkService
import me.scraplesh.courses.data.services.CbsEmailService
import me.scraplesh.courses.domain.services.EmailService
import me.scraplesh.courses.domain.services.NetworkService

@Module
@InstallIn(SingletonComponent::class)
abstract class ServiceModule {
    @Binds
    abstract fun bindNetworkService(service: AndroidNetworkService): NetworkService

    @Binds
    abstract fun bindEmailService(service: CbsEmailService): EmailService
}