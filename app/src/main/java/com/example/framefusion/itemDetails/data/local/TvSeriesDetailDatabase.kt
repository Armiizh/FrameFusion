package com.example.framefusion.itemDetails.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.framefusion.itemDetails.data.local.dao.TvSeriesDetailsDao
import com.example.framefusion.itemDetails.data.local.models.TvSeriesDetails

@Database(entities = [TvSeriesDetails::class], version = 1)
abstract class TvSeriesDetailDatabase: RoomDatabase() {
    abstract fun tvSeriesDetailsDao(): TvSeriesDetailsDao
}