package com.example.framefusion.search.data.local.converters

import androidx.room.TypeConverter
import com.example.framefusion.search.data.local.models.ExternalId

class ExternalIdConverter {
    @TypeConverter
    fun fromExternalId(externalId: ExternalId?): String {
        return "${externalId?.kpHD ?: ""},${externalId?.imdb ?: ""},${externalId?.tmdb ?: ""}"
    }

    @TypeConverter
    fun toExternalId(externalIdString: String): ExternalId {
        val parts = externalIdString.split(",")
        return ExternalId(
            kpHD = parts.getOrElse(0) { "" },
            imdb = parts.getOrElse(1) { "" },
            tmdb = parts.getOrElse(2) { "" }
        )
    }
}