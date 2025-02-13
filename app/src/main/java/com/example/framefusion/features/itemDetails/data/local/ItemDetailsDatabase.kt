package com.example.framefusion.features.itemDetails.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.framefusion.features.home.data.local.converters.PosterConverter
import com.example.framefusion.features.itemDetails.data.local.convertes.BackdropConverter
import com.example.framefusion.features.itemDetails.data.local.convertes.GenreListConverter
import com.example.framefusion.features.itemDetails.data.local.convertes.PersonListConverter
import com.example.framefusion.features.itemDetails.data.local.convertes.RatingConverter
import com.example.framefusion.features.itemDetails.data.local.dao.ItemDetailsDao
import com.example.framefusion.features.itemDetails.data.local.models.ItemDetails

@Database(entities = [ItemDetails::class], version = 1)
@TypeConverters(
    PosterConverter::class,
    GenreListConverter::class,
    BackdropConverter::class,
    RatingConverter::class,
    PersonListConverter::class
)
abstract class ItemDetailsDatabase : RoomDatabase() {
    abstract fun itemDetailsDao(): ItemDetailsDao
}