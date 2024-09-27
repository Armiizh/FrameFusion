package com.example.framefusion.home.data.local.converters

import androidx.room.TypeConverter
import com.example.framefusion.home.data.local.model.Genre

class GenreListConverter {
    @TypeConverter
    fun fromGenreList(genres: List<Genre>): String {
        return genres.joinToString { it.name.toString() }
    }

    @TypeConverter
    fun toGenreList(genresString: String): List<Genre> {
        return genresString.split(",").map { Genre(it) }
    }
}