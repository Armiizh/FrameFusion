package com.example.framefusion.home.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.framefusion.home.data.local.dao.Top10PersonalTvSeriesDao
import com.example.framefusion.home.data.local.models.Top10PersonalTvSeries

@Database(entities = [Top10PersonalTvSeries::class], version = 1)
abstract class Top10PersonalTvSeriesDatabase : RoomDatabase() {
    abstract fun top10PersonalTvSeriesDao(): Top10PersonalTvSeriesDao
}