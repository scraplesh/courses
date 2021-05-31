package me.scraplesh.courses.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import me.scraplesh.courses.data.repo.CoursesUserRepository
import me.scraplesh.courses.data.repo.RemoteCoursesRepository
import me.scraplesh.courses.domain.repo.CoursesRepository
import me.scraplesh.courses.domain.repo.UserRepository

@InstallIn(SingletonComponent::class)
@Module
abstract class RepositoryModule {
    @Binds
    abstract fun bindUserRepository(repo: CoursesUserRepository): UserRepository

    @Binds
    abstract fun bindCoursesRepository(repo: RemoteCoursesRepository): CoursesRepository
}