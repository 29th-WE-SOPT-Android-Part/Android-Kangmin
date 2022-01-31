package org.sopt.soptandroidseminar.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import org.sopt.soptandroidseminar.data.service.GithubApiService
import org.sopt.soptandroidseminar.data.service.SoptApiService
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ServiceModule {

    @Provides
    @Singleton
    fun provideGithubService(@RetrofitModule.GithubRetrofit retrofit: Retrofit): GithubApiService =
        retrofit.create(GithubApiService::class.java)

    @Provides
    @Singleton
    fun provideSoptService(@RetrofitModule.SoptRetrofit retrofit: Retrofit): SoptApiService =
        retrofit.create(SoptApiService::class.java)
}