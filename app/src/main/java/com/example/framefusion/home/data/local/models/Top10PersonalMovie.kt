package com.example.framefusion.home.data.local.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.framefusion.home.data.local.converters.PosterConverter
import com.google.gson.annotations.SerializedName

@Entity(tableName = "movies")
@TypeConverters(
    PosterConverter::class
)
data class Top10PersonalMovie(
    @PrimaryKey
    @SerializedName("id")
    val id: Int? = 0,
    @SerializedName("poster")
    val poster: Poster
)