package com.example.framefusion.itemDetails.data.local.convertes

import androidx.room.TypeConverter
import com.example.framefusion.itemDetails.data.local.models.Rating
import com.google.gson.Gson

class RatingConverter {

    private val gson = Gson()

    @TypeConverter
    fun fromRating(rating: Rating): String {
        return gson.toJson(rating)
    }

    @TypeConverter
    fun toRating(ratingString: String): Rating? {
        if (ratingString == null || ratingString == "null") {
            return null
        }
        return try {
            gson.fromJson(ratingString, Rating::class.java)
        } catch (e: Exception) {
            null
        }
    }
}