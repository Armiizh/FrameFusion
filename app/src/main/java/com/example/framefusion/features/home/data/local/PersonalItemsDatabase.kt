package com.example.framefusion.features.home.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.framefusion.features.home.data.local.converters.PosterConverter
import com.example.framefusion.features.home.data.local.dao.PersonalItemsDao
import com.example.framefusion.features.home.data.local.models.PersonalItems

@Database(entities = [PersonalItems::class], version = 1)
@TypeConverters(PosterConverter::class)
abstract class PersonalItemsDatabase : RoomDatabase() {
    abstract fun personalItemsDao(): PersonalItemsDao
}