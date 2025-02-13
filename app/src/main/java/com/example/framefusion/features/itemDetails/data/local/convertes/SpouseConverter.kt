package com.example.framefusion.features.itemDetails.data.local.convertes

import androidx.room.TypeConverter
import com.example.framefusion.features.itemDetails.data.local.models.Spouse
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class SpouseConverter {

    private val gson = Gson()

    @TypeConverter
    fun fromSpouseList(spouses: List<Spouse>?): String? {
        return gson.toJson(spouses)
    }

    @TypeConverter
    fun toSpouseList(data: String?): List<Spouse>? {
        val listType = object : TypeToken<List<Spouse>>() {}.type
        return gson.fromJson(data, listType)
    }
}