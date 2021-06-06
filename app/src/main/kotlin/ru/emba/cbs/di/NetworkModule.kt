package ru.emba.cbs.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ru.emba.cbs.BuildConfig
import ru.emba.cbs.data.CbsApi
import ru.emba.cbs.data.CbsAuthApi
import ru.emba.cbs.data.FlowCallAdapterFactory
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import ru.emba.cbs.lib.Oauth2Authenticator
import ru.emba.cbs.lib.Oauth2Interceptor

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {
    @Provides
    fun authService(
        okHttpClientBuilder: OkHttpClient.Builder,
        oauth2Interceptor: Oauth2Interceptor,
        oauth2Authenticator: Oauth2Authenticator
    ): CbsAuthApi {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.authUrl)
            .client(okHttpClientBuilder.build())
            .addCallAdapterFactory(FlowCallAdapterFactory())
//            .addConverterFactory(MoshiConverterFactory.create(get()))
            .build()
            .create(CbsAuthApi::class.java)
    }

    @Provides
    fun apiService(
        okHttpClientBuilder: OkHttpClient.Builder,
        oauth2Interceptor: Oauth2Interceptor,
        oauth2Authenticator: Oauth2Authenticator
    ): CbsApi {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.apiUrl)
            .client(
                okHttpClientBuilder
                    .addInterceptor(oauth2Interceptor)
                    .authenticator(oauth2Authenticator)
                    .build()
            )
            .addCallAdapterFactory(FlowCallAdapterFactory())
//            .addConverterFactory(MoshiConverterFactory.create(get()))
            .build()
            .create(CbsApi::class.java)
    }
}