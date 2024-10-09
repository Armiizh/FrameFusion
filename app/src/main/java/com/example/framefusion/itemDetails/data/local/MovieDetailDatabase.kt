package com.example.framefusion.itemDetails.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.framefusion.itemDetails.data.local.dao.MovieDetailsDao
import com.example.framefusion.itemDetails.data.local.models.MovieDetails

@Database(entities = [MovieDetails::class], version = 1)
abstract class MovieDetailDatabase: RoomDatabase() {
    abstract fun movieDetailsDao(): MovieDetailsDao
}