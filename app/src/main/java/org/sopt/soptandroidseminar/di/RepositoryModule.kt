package org.sopt.soptandroidseminar.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import org.sopt.soptandroidseminar.data.repository.AuthRepositoryImpl
import org.sopt.soptandroidseminar.domain.AuthRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {
    @Provides
    @Singleton
    fun provideLoginRepository(authRepository: AuthRepositoryImpl) : AuthRepository = authRepository
}