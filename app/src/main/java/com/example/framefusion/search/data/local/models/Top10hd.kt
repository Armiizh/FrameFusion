package com.example.framefusion.search.data.local.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.framefusion.home.data.local.converters.PosterConverter
import com.example.framefusion.home.data.local.models.Poster

@Entity(tableName = "top10hd")
@TypeConverters(
    PosterConverter::class
)
data class Top10hd(
    @PrimaryKey
    val displayId: Int ?= 0,
    val id: Int? = 0,
    val name: String? = "",
    val poster: Poster
)