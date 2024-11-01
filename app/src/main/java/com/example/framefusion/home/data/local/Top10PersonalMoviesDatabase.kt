package com.example.framefusion.home.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.framefusion.home.data.local.dao.Top10PersonalMoviesDao
import com.example.framefusion.home.data.local.models.Top10PersonalMovie

@Database(entities = [Top10PersonalMovie::class], version = 1)
abstract class Top10PersonalMoviesDatabase : RoomDatabase() {
    abstract fun top10PersonalMoviesDao(): Top10PersonalMoviesDao
}