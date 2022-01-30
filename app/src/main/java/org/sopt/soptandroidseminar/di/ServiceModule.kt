package org.sopt.soptandroidseminar.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import org.sopt.soptandroidseminar.api.GithubApiService
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ServiceModule {
    @Provides
    @Singleton
    fun provideGithubService(retrofit: Retrofit): GithubApiService =
        retrofit.create(GithubApiService::class.java)
}