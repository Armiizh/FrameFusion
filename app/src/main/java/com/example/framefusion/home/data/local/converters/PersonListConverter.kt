package com.example.framefusion.home.data.local.converters

import androidx.room.TypeConverter
import com.example.framefusion.home.data.local.model.Person

class PersonListConverter {
    @TypeConverter
    fun fromPersonList(persons: List<Person>): String {
        return persons.joinToString { "${it.id},${it.photo},${it.name},${it.enName},${it.description},${it.profession},${it.enProfession}" }
    }

    @TypeConverter
    fun toPersonList(personsString: String): List<Person> {
        return personsString.split(",").map {
            val parts = it.split(",")
            Person(
                id = parts[0].toInt(),
                photo = parts[1],
                name = parts[2],
                enName = parts[3],
                description = parts[4],
                profession = parts[5],
                enProfession = parts[6]
            )
        }
    }
}