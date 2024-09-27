package com.example.framefusion.home.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.framefusion.home.data.local.dao.HomeDao
import com.example.framefusion.home.data.local.model.Movie

@Database(entities = [Movie::class], version = 1)
abstract class HomeDatabase: RoomDatabase() {
    abstract fun homeDao(): HomeDao
}