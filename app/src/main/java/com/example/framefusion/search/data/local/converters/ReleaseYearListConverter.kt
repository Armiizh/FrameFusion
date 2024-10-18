package com.example.framefusion.search.data.local.converters

import androidx.room.TypeConverter
import com.example.framefusion.search.data.local.models.ReleaseYear
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class ReleaseYearListConverter {
    private val gson = Gson()

    @TypeConverter
    fun fromReleaseYearList(releaseYears: List<ReleaseYear>): String {
        return gson.toJson(releaseYears)
    }

    @TypeConverter
    fun toReleaseYearList(releaseYearString: String): List<ReleaseYear> {
        val listType = object : TypeToken<List<ReleaseYear>>() {}.type
        return gson.fromJson(releaseYearString, listType)
    }
}