package com.example.framefusion.itemDetails.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.framefusion.itemDetails.data.local.dao.ActorDetailsDao
import com.example.framefusion.itemDetails.data.local.models.ActorDetails

@Database(entities = [ActorDetails::class], version = 1)
abstract class ActorDetailsDatabase : RoomDatabase() {
    abstract fun actorDetailsDao(): ActorDetailsDao
}