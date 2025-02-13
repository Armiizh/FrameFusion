package com.example.framefusion.features.home.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.framefusion.features.home.data.local.converters.PosterConverter
import com.example.framefusion.features.home.data.local.dao.Top10PersonalTvSeriesDao
import com.example.framefusion.features.home.data.local.models.Top10PersonalTvSeries

@Database(entities = [Top10PersonalTvSeries::class], version = 1)
@TypeConverters(PosterConverter::class)
abstract class Top10PersonalTvSeriesDatabase : RoomDatabase() {
    abstract fun top10PersonalTvSeriesDao(): Top10PersonalTvSeriesDao
}