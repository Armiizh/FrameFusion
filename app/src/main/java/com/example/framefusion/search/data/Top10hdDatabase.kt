package com.example.framefusion.search.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.framefusion.search.data.local.dao.Top10hdDao
import com.example.framefusion.search.data.local.models.Top10hd

@Database(entities = [Top10hd::class], version = 1)
abstract class Top10hdDatabase: RoomDatabase() {
    abstract fun top10hdDao(): Top10hdDao
}