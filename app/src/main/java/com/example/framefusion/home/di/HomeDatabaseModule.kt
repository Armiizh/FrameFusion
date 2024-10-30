package com.example.framefusion.home.di

import android.content.Context
import androidx.room.Room
import com.example.framefusion.home.data.local.PersonalMoviesDatabase
import com.example.framefusion.home.data.local.PersonalTvSeriesDatabase
import com.example.framefusion.home.data.local.Top10PersonalMoviesDatabase
import com.example.framefusion.home.data.local.Top10PersonalTvSeriesDatabase
import com.example.framefusion.home.data.local.dao.PersonalMoviesDao
import com.example.framefusion.home.data.local.dao.PersonalTvSeriesDao
import com.example.framefusion.home.data.local.dao.Top10PersonalTvSeriesDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object HomeDatabaseModule {

    //Top 10 Personal Movies
    @Provides
    fun provideTop10PersonalMoviesDatabase(@ApplicationContext appContext: Context): Top10PersonalMoviesDatabase {
        return Room.databaseBuilder(appContext, Top10PersonalMoviesDatabase::class.java, "movies")
            .build()
    }

    @Provides
    fun provideTop10PersonalMoviesDao(personalMoviesDatabase: PersonalMoviesDatabase): PersonalMoviesDao {
        return personalMoviesDatabase.personalMoviesDao()
    }

    //All Personal Movies
    @Provides
    fun providePersonalMoviesDatabase(@ApplicationContext appContext: Context): PersonalMoviesDatabase {
        return Room.databaseBuilder(appContext, PersonalMoviesDatabase::class.java, "all_movies")
            .build()
    }
    @Provides
    fun providePersonalMoviesDao(personalMoviesDatabase: PersonalMoviesDatabase): PersonalMoviesDao {
        return personalMoviesDatabase.personalMoviesDao()
    }

    //Top 10 Personal Tv-series
    @Provides
    fun provideTop10PersonalTvSeriesDatabase(@ApplicationContext appContext: Context): Top10PersonalTvSeriesDatabase {
        return Room.databaseBuilder(
            appContext,
            Top10PersonalTvSeriesDatabase::class.java,
            "tv_series"
        )
            .build()
    }

    @Provides
    fun provideTop10PersonalTvSeriesDao(top10PersonalTvSeriesDatabase: Top10PersonalTvSeriesDatabase): Top10PersonalTvSeriesDao {
        return top10PersonalTvSeriesDatabase.top10PersonalTvSeriesDao()
    }

    //Personal Tv-series
    @Provides
    fun providePersonalTvSeriesDatabase(@ApplicationContext appContext: Context): PersonalTvSeriesDatabase {
        return Room.databaseBuilder(
            appContext,
            PersonalTvSeriesDatabase::class.java,
            "all_tv_series"
        )
            .build()
    }

    @Provides
    fun providePersonalTvSeriesDao(personalTvSeriesDatabase: PersonalTvSeriesDatabase): PersonalTvSeriesDao {
        return personalTvSeriesDatabase.personalTvSeriesDao()
    }
}