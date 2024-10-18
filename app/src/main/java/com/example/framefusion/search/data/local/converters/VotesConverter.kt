package com.example.framefusion.search.data.local.converters


import androidx.room.TypeConverter
import com.example.framefusion.search.data.local.models.Votes
import com.google.gson.Gson

class VotesConverter {
    private val gson = Gson()

    @TypeConverter
    fun fromVotes(votes: Votes): String {
        return gson.toJson(votes)
    }

    @TypeConverter
    fun toVotes(votesString: String): Votes? {
        return gson.fromJson(votesString, Votes::class.java)
    }
}