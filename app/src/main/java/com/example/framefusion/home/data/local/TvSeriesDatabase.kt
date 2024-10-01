package com.example.framefusion.home.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.framefusion.home.data.local.localDao.HomeTvSeriesDao
import com.example.framefusion.home.data.local.models.TvSeries

@Database(entities = [TvSeries::class], version = 1)
abstract class TvSeriesDatabase: RoomDatabase() {
    abstract fun tvSeriesDao(): HomeTvSeriesDao
}