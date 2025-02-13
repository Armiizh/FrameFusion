package com.example.framefusion.features.greeting.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.framefusion.features.greeting.data.local.dao.UserGenresDao
import com.example.framefusion.features.greeting.data.local.model.UserGenres

@Database(entities = [UserGenres::class], version = 1)
abstract class UserGenresDatabase: RoomDatabase() {
    abstract fun userDao(): UserGenresDao
}