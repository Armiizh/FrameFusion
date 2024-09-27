package com.example.framefusion.home.data.local.converters

import androidx.room.TypeConverter
import com.example.framefusion.home.data.local.model.Backdrop

class BackdropConverter {
    @TypeConverter
    fun fromBackdrop(backdrop: Backdrop): String {
        return "${backdrop.url},${backdrop.previewUrl}"
    }

    @TypeConverter
    fun toBackdrop(backdropString: String): Backdrop {
        val parts = backdropString.split(",")
        return Backdrop(
            url = parts[0],
            previewUrl = parts[1]
        )
    }
}