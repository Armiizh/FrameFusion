package com.example.framefusion.itemDetails.data.local.convertes

import androidx.room.TypeConverter
import com.example.framefusion.itemDetails.data.local.models.Profession
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class ProfessionConverter {

    private val gson = Gson()

    @TypeConverter
    fun fromProfessionList(professions: List<Profession>?): String? {
        return gson.toJson(professions)
    }

    @TypeConverter
    fun toProfessionList(data: String?): List<Profession>? {
        val listType = object : TypeToken<List<Profession>>() {}.type
        return gson.fromJson(data, listType)
    }
}