package com.example.framefusion.search.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.framefusion.search.data.local.dao.SearchItemDao
import com.example.framefusion.search.data.local.models.SearchItem

@Database(entities = [SearchItem::class], version = 1)
abstract class ItemSearchDatabase: RoomDatabase() {
    abstract fun searchItemDao(): SearchItemDao
}