package com.example.framefusion.features.search.data

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.framefusion.features.home.data.local.converters.GenreListConverterForMovies
import com.example.framefusion.features.home.data.local.converters.PosterConverter
import com.example.framefusion.features.itemDetails.data.local.convertes.RatingConverter
import com.example.framefusion.features.search.data.local.dao.SearchItemDao
import com.example.framefusion.features.search.data.local.models.SearchItem

@Database(entities = [SearchItem::class], version = 1)
@TypeConverters(
    PosterConverter::class,
    RatingConverter::class,
    GenreListConverterForMovies::class,
)
abstract class ItemSearchDatabase: RoomDatabase() {
    abstract fun searchItemDao(): SearchItemDao
}