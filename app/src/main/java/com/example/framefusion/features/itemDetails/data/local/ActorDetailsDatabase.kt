package com.example.framefusion.features.itemDetails.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.framefusion.features.itemDetails.data.local.convertes.ActorsMovieConverter
import com.example.framefusion.features.itemDetails.data.local.convertes.BirthPlaceConverter
import com.example.framefusion.features.itemDetails.data.local.convertes.DeathPlaceConverter
import com.example.framefusion.features.itemDetails.data.local.convertes.FactsConverter
import com.example.framefusion.features.itemDetails.data.local.convertes.ProfessionConverter
import com.example.framefusion.features.itemDetails.data.local.convertes.SpouseConverter
import com.example.framefusion.features.itemDetails.data.local.dao.ActorDetailsDao
import com.example.framefusion.features.itemDetails.data.local.models.ActorDetails

@Database(entities = [ActorDetails::class], version = 1)
@TypeConverters(
    BirthPlaceConverter::class,
    DeathPlaceConverter::class,
    FactsConverter::class,
    ActorsMovieConverter::class,
    ProfessionConverter::class,
    SpouseConverter::class
)
abstract class ActorDetailsDatabase : RoomDatabase() {
    abstract fun actorDetailsDao(): ActorDetailsDao
}