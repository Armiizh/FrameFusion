package com.example.framefusion.features.home.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.framefusion.features.home.data.local.converters.PosterConverter
import com.example.framefusion.features.home.data.local.dao.Top10PersonalMoviesDao
import com.example.framefusion.features.home.data.local.models.Top10PersonalMovie

@Database(entities = [Top10PersonalMovie::class], version = 1)
@TypeConverters(PosterConverter::class)
abstract class Top10PersonalMoviesDatabase : RoomDatabase() {
    abstract fun top10PersonalMoviesDao(): Top10PersonalMoviesDao
}