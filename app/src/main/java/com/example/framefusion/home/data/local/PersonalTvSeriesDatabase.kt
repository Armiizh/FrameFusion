package com.example.framefusion.home.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.framefusion.home.data.local.dao.PersonalTvSeriesDao
import com.example.framefusion.home.data.local.models.PersonalTvSeries

@Database(entities = [PersonalTvSeries::class], version = 1)
abstract class PersonalTvSeriesDatabase : RoomDatabase() {
    abstract fun personalTvSeriesDao(): PersonalTvSeriesDao
}