package com.example.framefusion.features.search.data.local.converters


import androidx.room.TypeConverter
import com.example.framefusion.features.search.data.local.models.Country
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class CountryListConverter {
    private val gson = Gson()

    @TypeConverter
    fun fromCountryList(countries: List<Country>): String {
        return gson.toJson(countries)
    }

    @TypeConverter
    fun toCountryList(countryString: String): List<Country> {
        val listType = object : TypeToken<List<Country>>() {}.type
        return gson.fromJson(countryString, listType)
    }
}