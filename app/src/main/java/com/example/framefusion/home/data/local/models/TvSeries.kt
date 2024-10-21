package com.example.framefusion.home.data.local.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.framefusion.home.data.local.converters.GenreListConverterForMovies
import com.example.framefusion.home.data.local.converters.PosterConverter

@Entity(tableName = "tv_series")
@TypeConverters(
    PosterConverter::class
)
data class TvSeries(
    @PrimaryKey
    val id: Int? = 0,
    val poster: Poster
)