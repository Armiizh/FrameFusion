package com.example.framefusion.itemDetails.di

import android.content.Context
import androidx.room.Room
import com.example.framefusion.itemDetails.data.local.MovieDetailDatabase
import com.example.framefusion.itemDetails.data.local.TvSeriesDetailDatabase
import com.example.framefusion.itemDetails.data.local.dao.MovieDetailsDao
import com.example.framefusion.itemDetails.data.local.dao.TvSeriesDetailsDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object DetailsDatabaseModule {

    @Provides
    fun provideMovieDetailsDatabase(@ApplicationContext appContext: Context): MovieDetailDatabase {
        return Room.databaseBuilder(appContext, MovieDetailDatabase::class.java, "movie_details")
            .build()
    }

    @Provides
    fun provideMovieDetailsDao(movieDetailDatabase: MovieDetailDatabase): MovieDetailsDao {
        return movieDetailDatabase.movieDetailsDao()
    }

    @Provides
    fun provideTvSeriesDetailsDatabase(@ApplicationContext appContext: Context): TvSeriesDetailDatabase {
        return Room.databaseBuilder(appContext, TvSeriesDetailDatabase::class.java, "tv_series_details")
            .build()
    }

    @Provides
    fun provideTvSeriesDetailsDao(tvSeriesDetailDatabase: TvSeriesDetailDatabase): TvSeriesDetailsDao {
        return tvSeriesDetailDatabase.tvSeriesDetailsDao()
    }
}