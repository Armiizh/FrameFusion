package com.example.framefusion.home.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.framefusion.home.data.local.dao.PersonalItemsDao
import com.example.framefusion.home.data.local.models.PersonalItems

@Database(entities = [PersonalItems::class], version = 1)
abstract class PersonalItemsDatabase : RoomDatabase() {
    abstract fun personalItemsDao(): PersonalItemsDao
}