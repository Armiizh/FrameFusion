package com.example.framefusion.home.di

import android.content.Context
import androidx.room.Room
import com.example.framefusion.home.data.local.HomeDatabase
import com.example.framefusion.home.data.local.localDao.LocalHomeDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object HomeDatabaseModule {

    @Provides
    fun provideDatabase(@ApplicationContext appContext: Context): HomeDatabase {
        return Room.databaseBuilder(appContext, HomeDatabase::class.java, "movie")
            .build()
    }

    @Provides
    fun provideHomeDao(database: HomeDatabase): LocalHomeDao {
        return database.homeDao()
    }
}