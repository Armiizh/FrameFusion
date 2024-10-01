package com.example.framefusion.home.di

import android.content.Context
import androidx.room.Room
import com.example.framefusion.home.data.local.MovieDatabase
import com.example.framefusion.home.data.local.TvSeriesDatabase
import com.example.framefusion.home.data.local.localDao.HomeMovieDao
import com.example.framefusion.home.data.local.localDao.HomeTvSeriesDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object HomeDatabaseModule {

    @Provides
    fun provideMovieDatabase(@ApplicationContext appContext: Context): MovieDatabase {
        return Room.databaseBuilder(appContext, MovieDatabase::class.java, "movie")
            .build()
    }

    @Provides
    fun provideHomeMovieDao(movieDatabase: MovieDatabase): HomeMovieDao {
        return movieDatabase.homeDao()
    }

    @Provides
    fun provideTvSeriesDatabase(@ApplicationContext appContext: Context): TvSeriesDatabase {
        return Room.databaseBuilder(appContext, TvSeriesDatabase::class.java, "tv_series")
            .build()
    }

    @Provides
    fun provideHomeTvSeriesDao(tvSeriesDatabase: TvSeriesDatabase): HomeTvSeriesDao {
        return tvSeriesDatabase.tvSeriesDao()
    }
}