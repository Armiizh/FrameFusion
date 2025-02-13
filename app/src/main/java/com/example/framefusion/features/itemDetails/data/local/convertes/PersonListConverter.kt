package com.example.framefusion.features.itemDetails.data.local.convertes

import androidx.room.TypeConverter
import com.example.framefusion.features.itemDetails.data.local.models.Person

class PersonListConverter {
    @TypeConverter
    fun fromPersonList(persons: List<Person>): String {
        return persons.joinToString { "${it.id},${it.photo},${it.name},${it.enName},${it.description},${it.profession},${it.enProfession}" }
    }

    @TypeConverter
    fun toPersonList(personsString: String): List<Person>? {
        if (personsString == null || personsString == "null") {
            return emptyList()
        }
        val persons = personsString.split(",")
        if (persons.size % 7 != 0) {
            return emptyList()
        }
        return try {
            persons.filter { it.trim().isNotEmpty() }.chunked(7).map {
                Person(
                    id = it[0].trim().toInt(),
                    photo = it[1].trim(),
                    name = it[2].trim().takeIf { it.isNotEmpty() },
                    enName = it[3].trim().takeIf { it.isNotEmpty() },
                    description = it[4].trim().takeIf { it.isNotEmpty() },
                    profession = it[5].trim().takeIf { it.isNotEmpty() },
                    enProfession = it[6].trim().takeIf { it.isNotEmpty() }
                )
            }
        } catch (e: NumberFormatException) {
            emptyList()
        }
    }
}