package com.example.framefusion.home.data.local.converters

import androidx.room.TypeConverter
import com.example.framefusion.home.data.local.models.Rating

class RatingConverter {
    @TypeConverter
    fun fromRating(rating: Rating): String {
        return "${rating.kp},${rating.imdb},${rating.filmCritics},${rating.russianFilmCritics},${rating.await}"
    }

    @TypeConverter
    fun toRating(ratingString: String): Rating? {
        if (ratingString == null || ratingString == "null") {
            return Rating(
                kp = 0.0,
                imdb = 0.0,
                filmCritics = 0.0,
                russianFilmCritics = 0.0,
                await = 0.0
            )
        }
        val parts = ratingString.split(",")
        if (parts.size < 5) {
            return Rating(
                kp = 0.0,
                imdb = 0.0,
                filmCritics = 0.0,
                russianFilmCritics = 0.0,
                await = 0.0
            )
        }
        return try {
            Rating(
                kp = parts[0].toDouble(),
                imdb = parts[1].toDouble(),
                filmCritics = parts[2].toDouble(),
                russianFilmCritics = parts[3].toDouble(),
                await = parts[4].toDouble()
            )
        } catch (e: NumberFormatException) {
            Rating(
                kp = 0.0,
                imdb = 0.0,
                filmCritics = 0.0,
                russianFilmCritics = 0.0,
                await = 0.0
            )
        }
    }
}