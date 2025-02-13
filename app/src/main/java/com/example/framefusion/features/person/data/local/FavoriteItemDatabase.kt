package com.example.framefusion.features.person.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.framefusion.features.person.data.local.dao.FavoriteItemDao
import com.example.framefusion.features.person.data.local.model.FavoriteItem

@Database(entities = [FavoriteItem::class], version = 1)
abstract class FavoriteItemDatabase : RoomDatabase() {
    abstract fun favoriteItemDao(): FavoriteItemDao
}