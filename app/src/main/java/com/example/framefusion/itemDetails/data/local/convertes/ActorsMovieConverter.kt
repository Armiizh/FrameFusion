package com.example.framefusion.itemDetails.data.local.convertes

import androidx.room.TypeConverter
import com.example.framefusion.itemDetails.data.local.models.ActorsMovie
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class ActorsMovieConverter {
    private val gson = Gson()

    @TypeConverter
    fun fromActorsMovieList(movies: List<ActorsMovie>?): String? {
        return gson.toJson(movies)
    }

    @TypeConverter
    fun toActorsMovieList(data: String?): List<ActorsMovie>? {
        val listType = object : TypeToken<List<ActorsMovie>>() {}.type
        return gson.fromJson(data, listType)
    }
}