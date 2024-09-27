package com.example.framefusion.home.data.local.converters

import androidx.room.TypeConverter
import com.example.framefusion.home.data.local.model.Rating

class RatingConverter {
    @TypeConverter
    fun fromRating(rating: Rating): String {
        return "${rating.kp},${rating.imdb},${rating.filmCritics},${rating.russianFilmCritics},${rating.await}"
    }

    @TypeConverter
    fun toRating(ratingString: String): Rating {
        val parts = ratingString.split(",")
        return Rating(
            kp = parts[0].toDouble(),
            imdb = parts[1].toDouble(),
            filmCritics = parts[2].toDouble(),
            russianFilmCritics = parts[3].toDouble(),
            await = parts[4].toDouble()
        )
    }
}