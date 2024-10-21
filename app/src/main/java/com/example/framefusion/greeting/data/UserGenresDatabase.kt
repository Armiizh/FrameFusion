package com.example.framefusion.greeting.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.framefusion.greeting.data.dao.UserGenresDao
import com.example.framefusion.greeting.data.model.UserGenres

@Database(entities = [UserGenres::class], version = 1)
abstract class UserGenresDatabase: RoomDatabase() {
    abstract fun userDao(): UserGenresDao
}