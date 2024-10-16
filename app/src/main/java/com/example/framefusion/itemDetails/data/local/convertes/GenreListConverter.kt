package com.example.framefusion.itemDetails.data.local.convertes

import androidx.room.TypeConverter
import com.example.framefusion.home.data.local.models.Genre

class GenreListConverter {
    @TypeConverter
    fun fromGenreList(genres: List<Genre>): String {
        return genres.joinToString { it.name.toString() }
    }

    @TypeConverter
    fun toGenreList(genresString: String): List<Genre> {
        if (genresString == null || genresString == "null") {
            return emptyList()
        }
        return genresString.split(",").map { Genre(it) }
    }
}