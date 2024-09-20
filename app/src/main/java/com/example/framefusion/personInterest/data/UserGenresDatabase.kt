package com.example.framefusion.personInterest.data

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [UserGenres::class], version = 1)
abstract class UserGenresDatabase: RoomDatabase() {
    abstract fun userDao(): UserGenresDao
}