package com.example.framefusion.features.greeting.domain.module

import com.example.framefusion.features.greeting.data.GenresRepository
import com.example.framefusion.features.greeting.data.GenresRepositoryImpl
import com.example.framefusion.features.greeting.data.local.dao.UserGenresDao
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