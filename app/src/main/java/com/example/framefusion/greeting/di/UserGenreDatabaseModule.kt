package com.example.framefusion.greeting.di

import android.content.Context
import androidx.room.Room
import com.example.framefusion.greeting.data.dao.UserGenresDao
import com.example.framefusion.greeting.data.UserGenresDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object UserGenreDatabaseModule {

    @Provides
    fun provideUserGenresDatabase(@ApplicationContext appContext: Context):  UserGenresDatabase{
        return Room.databaseBuilder(appContext, UserGenresDatabase::class.java, "user_genres")
            .build()
    }

    @Provides
    fun provideUserGenresDao(database: UserGenresDatabase): UserGenresDao {
        return database.userDao()
    }
}