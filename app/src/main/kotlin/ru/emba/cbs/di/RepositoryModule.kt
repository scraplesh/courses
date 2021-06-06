package ru.emba.cbs.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ru.emba.cbs.data.repo.CoursesUserRepository
import ru.emba.cbs.data.repo.RemoteCoursesRepository

@InstallIn(SingletonComponent::class)
@Module
abstract class RepositoryModule {
    @Binds
    abstract fun bindUserRepository(repo: CoursesUserRepository): ru.emba.cbs.domain.repo.UserRepository

    @Binds
    abstract fun bindCoursesRepository(repo: RemoteCoursesRepository): ru.emba.cbs.domain.repo.CoursesRepository
}