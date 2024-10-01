package com.example.framefusion.home.data.local.converters

import androidx.room.TypeConverter
import com.example.framefusion.home.data.local.models.Person

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
            persons.chunked(7).map {
                Person(
                    id = it[0].toInt(),
                    photo = it[1],
                    name = it[2],
                    enName = it[3],
                    description = it[4],
                    profession = it[5],
                    enProfession = it[6]
                )
            }
        } catch (e: NumberFormatException) {
            emptyList()
        }
    }
}