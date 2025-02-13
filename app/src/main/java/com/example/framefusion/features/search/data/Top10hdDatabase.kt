package com.example.framefusion.features.search.data

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.framefusion.features.home.data.local.converters.PosterConverter
import com.example.framefusion.features.search.data.local.dao.Top10hdDao
import com.example.framefusion.features.search.data.local.models.Top10hd

@Database(entities = [Top10hd::class], version = 1)
@TypeConverters(PosterConverter::class)
abstract class Top10hdDatabase: RoomDatabase() {
    abstract fun top10hdDao(): Top10hdDao
}