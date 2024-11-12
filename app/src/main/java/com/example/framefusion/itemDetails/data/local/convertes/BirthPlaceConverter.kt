package com.example.framefusion.itemDetails.data.local.convertes

import androidx.room.TypeConverter
import com.example.framefusion.itemDetails.data.local.models.BirthPlace
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class BirthPlaceConverter {
    private val gson = Gson()

    @TypeConverter
    fun fromBirthPlaceList(birthPlaces: List<BirthPlace>?): String? {
        return gson.toJson(birthPlaces)
    }

    @TypeConverter
    fun toBirthPlaceList(data: String?): List<BirthPlace>? {
        val listType = object : TypeToken<List<BirthPlace>>() {}.type
        return gson.fromJson(data, listType)
    }
}