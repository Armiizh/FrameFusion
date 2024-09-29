package com.example.framefusion.home.data.local.converters

import androidx.room.TypeConverter
import com.example.framefusion.home.data.local.model.Poster

class PosterConverter {
    @TypeConverter
    fun fromPoster(poster: Poster): String {
        return "${poster.url},${poster.previewUrl}"
    }

    @TypeConverter
    fun toPoster(posterString: String): Poster? {
        if (posterString == null || posterString == "null") {
            return Poster("", "")
        }
        val parts = posterString.split(",")
        if (parts.size < 2) {
            return Poster("", "")
        }
        return Poster(
            url = parts[0],
            previewUrl = parts[1]
        )
    }
}
//
//class PosterConverter {
//    @TypeConverter
//    fun fromPoster(poster: Poster): String {
//        return "${poster.url},${poster.previewUrl}"
//    }
//
//    @TypeConverter
//    fun toPoster(posterString: String): Poster {
//        val parts = posterString.split(",")
//        return Poster(
//            url = parts[0],
//            previewUrl = parts[1]
//        )
//    }
//}