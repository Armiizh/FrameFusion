package com.example.framefusion.itemDetails.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.framefusion.itemDetails.data.local.dao.ItemDetailsDao
import com.example.framefusion.itemDetails.data.local.models.ItemDetails

@Database(entities = [ItemDetails::class], version = 1)
abstract class ItemDetailsDatabase: RoomDatabase() {
    abstract fun itemDetailsDao(): ItemDetailsDao
}