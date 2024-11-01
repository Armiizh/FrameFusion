package com.example.framefusion.home.di

import android.content.Context
import androidx.room.Room
import com.example.framefusion.home.data.local.PersonalItemsDatabase
import com.example.framefusion.home.data.local.Top10PersonalMoviesDatabase
import com.example.framefusion.home.data.local.Top10PersonalTvSeriesDatabase
import com.example.framefusion.home.data.local.dao.PersonalItemsDao
import com.example.framefusion.home.data.local.dao.Top10PersonalMoviesDao
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
    fun provideTop10PersonalMoviesDao(top10PersonalMoviesDatabase: Top10PersonalMoviesDatabase): Top10PersonalMoviesDao {
        return top10PersonalMoviesDatabase.top10PersonalMoviesDao()
    }

    //Top 10 Personal Tv-series
    @Provides
    fun provideTop10PersonalTvSeriesDatabase(@ApplicationContext appContext: Context): Top10PersonalTvSeriesDatabase {
        return Room.databaseBuilder(
            appContext,
            Top10PersonalTvSeriesDatabase::class.java,
            "tv_series"
        ).build()
    }
    @Provides
    fun provideTop10PersonalTvSeriesDao(top10PersonalTvSeriesDatabase: Top10PersonalTvSeriesDatabase): Top10PersonalTvSeriesDao {
        return top10PersonalTvSeriesDatabase.top10PersonalTvSeriesDao()
    }

    //All Personal Items
    @Provides
    fun providePersonalMoviesDatabase(@ApplicationContext appContext: Context): PersonalItemsDatabase {
        return Room.databaseBuilder(appContext, PersonalItemsDatabase::class.java, "all_items")
            .build()
    }
    @Provides
    fun providePersonalMoviesDao(personalItemsDatabase: PersonalItemsDatabase): PersonalItemsDao {
        return personalItemsDatabase.personalItemsDao()
    }
}