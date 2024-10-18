package com.example.framefusion.search.data.local.converters

import androidx.room.TypeConverter
import com.example.framefusion.search.data.local.models.Name

class NameListConverter {
    @TypeConverter
    fun fromNameList(names: List<Name>): String {
        return names.joinToString(separator = ";") { "${it.name},${it.language},${it.type}" }
    }

    @TypeConverter
    fun toNameList(nameString: String): List<Name> {
        return nameString.split(";").map {
            val parts = it.split(",")
            Name(
                name = parts.getOrElse(0) { "" },
                language = parts.getOrElse(1) { "" },
                type = parts.getOrElse(2) { "" }
            )
        }
    }
}