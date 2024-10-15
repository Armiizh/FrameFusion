package com.example.framefusion.home.data.local.converters

import android.util.Log
import androidx.room.TypeConverter
import com.example.framefusion.home.data.local.models.Genre

class GenreListConverterForMovies {
    @TypeConverter
    fun fromGenreList(genres: List<Genre>): String {
        return genres.joinToString { it.name.toString() } ?: ""
    }

    @TypeConverter
    fun toGenreList(genresString: String): List<Genre> {
        if (genresString == null || genresString == "null") {
            return emptyList()
        }
        return genresString.split(",").map { Genre(it) } ?: emptyList()
    }
}