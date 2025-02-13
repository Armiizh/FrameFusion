package com.example.framefusion.features.home.data.local.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "tv_series")
data class Top10PersonalTvSeries(
    @PrimaryKey
    @SerializedName("id")
    val id: Int? = 0,
    @SerializedName("poster")
    val poster: Poster
)