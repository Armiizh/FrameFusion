package com.example.framefusion.home.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.framefusion.home.data.local.localDao.LocalHomeDao
import com.example.framefusion.home.data.local.model.Movie

@Database(entities = [Movie::class], version = 1)
abstract class HomeDatabase: RoomDatabase() {
    abstract fun homeDao(): LocalHomeDao
}