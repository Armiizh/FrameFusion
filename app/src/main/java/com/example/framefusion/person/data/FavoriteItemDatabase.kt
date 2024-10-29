package com.example.framefusion.person.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.framefusion.person.data.dao.FavoriteItemDao
import com.example.framefusion.person.data.model.FavoriteItem

@Database(entities = [FavoriteItem::class], version = 1)
abstract class FavoriteItemDatabase : RoomDatabase() {
    abstract fun favoriteItemDao(): FavoriteItemDao
}