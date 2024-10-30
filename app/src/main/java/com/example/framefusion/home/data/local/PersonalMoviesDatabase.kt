package com.example.framefusion.home.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.framefusion.home.data.local.dao.PersonalMoviesDao
import com.example.framefusion.home.data.local.models.PersonalMovies

@Database(entities = [PersonalMovies::class], version = 1)
abstract class PersonalMoviesDatabase : RoomDatabase() {
    abstract fun personalMoviesDao(): PersonalMoviesDao
}