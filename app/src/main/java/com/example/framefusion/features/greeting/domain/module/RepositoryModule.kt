package com.example.framefusion.features.greeting.domain.module

import com.example.framefusion.features.greeting.data.local.dao.UserGenresDao
import com.example.framefusion.features.greeting.data.repository.GenresRepository
import com.example.framefusion.features.greeting.data.repository.GenresRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideGenresRepository(userGenresDao: UserGenresDao): GenresRepository {
        return GenresRepositoryImpl(userGenresDao)
    }
}