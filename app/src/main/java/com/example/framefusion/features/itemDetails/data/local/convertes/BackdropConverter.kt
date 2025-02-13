package com.example.framefusion.features.itemDetails.data.local.convertes

import androidx.room.TypeConverter
import com.example.framefusion.features.itemDetails.data.local.models.Backdrop

class BackdropConverter {

    @TypeConverter
    fun fromBackdrop(backdrop: Backdrop): String {
        return "${backdrop.url},${backdrop.previewUrl}"
    }

    @TypeConverter
    fun toBackdrop(backdropString: String): Backdrop {
        if (backdropString == null || backdropString == "null") {
            return Backdrop("", "")
        }
        val parts = backdropString.split(",")
        if (parts.size < 2) {
            return Backdrop("", "")
        }
        return Backdrop(
            url = parts[0],
            previewUrl = parts[1]
        )
    }
}