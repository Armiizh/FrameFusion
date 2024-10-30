package com.example.framefusion.home.data.local.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.framefusion.home.data.local.converters.PosterConverter
import com.google.gson.annotations.SerializedName

@Entity(tableName = "all_tv_series")
@TypeConverters(
    PosterConverter::class
)
data class PersonalTvSeries(
    @PrimaryKey
    @SerializedName("id")
    val id: Int? = 0,
    @SerializedName("poster")
    val poster: Poster
)