package com.example.framefusion.features.itemDetails.data.local.convertes

import androidx.room.TypeConverter
import com.example.framefusion.features.itemDetails.data.local.models.DeathPlace
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class DeathPlaceConverter {
    private val gson = Gson()

    @TypeConverter
    fun fromDeathPlaceList(deathPlace: List<DeathPlace>?): String? {
        return gson.toJson(deathPlace)
    }

    @TypeConverter
    fun toDeathPlaceList(data: String?): List<DeathPlace>? {
        val listType = object : TypeToken<List<DeathPlace>>() {}.type
        return gson.fromJson(data, listType)
    }
}