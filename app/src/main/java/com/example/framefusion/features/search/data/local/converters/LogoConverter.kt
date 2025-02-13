package com.example.framefusion.features.search.data.local.converters

import androidx.room.TypeConverter
import com.example.framefusion.features.search.data.local.models.Logo

class LogoConverter {
    @TypeConverter
    fun fromLogo(logo: Logo?): String {
        return "${logo?.url ?: ""},${logo?.previewUrl ?: ""}"
    }

    @TypeConverter
    fun toLogo(logoString: String): Logo {
        val parts = logoString.split(",")
        return Logo(
            url = parts.getOrElse(0) { "" },
            previewUrl = parts.getOrElse(1) { "" }
        )
    }
}