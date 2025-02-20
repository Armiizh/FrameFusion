package com.example.framefusion.features.person.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.framefusion.features.itemDetails.data.local.convertes.ProfessionConverter
import com.example.framefusion.features.person.data.local.dao.FavoriteActorDao
import com.example.framefusion.features.person.data.local.model.FavoriteActor

@Database(entities = [FavoriteActor::class], version = 1)
@TypeConverters(
    ProfessionConverter::class,
)
abstract class FavoriteActorDatabase : RoomDatabase() {
    abstract fun favoriteActorDao(): FavoriteActorDao
}