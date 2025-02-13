package com.example.framefusion.features.itemDetails.data.local.convertes

import androidx.room.TypeConverter
import com.example.framefusion.features.itemDetails.data.local.models.Facts
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class FactsConverter {
    private val gson = Gson()

    @TypeConverter
    fun fromFactsList(facts: List<Facts>?): String? {
        return gson.toJson(facts)
    }

    @TypeConverter
    fun toFactsList(data: String?): List<Facts>? {
        val listType = object : TypeToken<List<Facts>>() {}.type
        return gson.fromJson(data, listType)
    }
}