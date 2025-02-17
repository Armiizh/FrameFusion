package com.example.framefusion.features.person.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.framefusion.features.home.data.local.converters.PosterConverter
import com.example.framefusion.features.itemDetails.data.local.convertes.GenreListConverter
import com.example.framefusion.features.itemDetails.data.local.convertes.RatingConverter
import com.example.framefusion.features.person.data.local.dao.FavoriteItemDao
import com.example.framefusion.features.person.data.local.model.FavoriteItem

@Database(entities = [FavoriteItem::class], version = 1)
@TypeConverters(
    PosterConverter::class,
    GenreListConverter::class,
    RatingConverter::class,
)
abstract class FavoriteItemDatabase : RoomDatabase() {
    abstract fun favoriteItemDao(): FavoriteItemDao
}