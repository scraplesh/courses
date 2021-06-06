package ru.emba.cbs.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient

@Module
@InstallIn(SingletonComponent::class)
class OkHttpModule {
    @Provides
    fun okHttpDebug(): OkHttpClient.Builder = OkHttpClient.Builder()
}