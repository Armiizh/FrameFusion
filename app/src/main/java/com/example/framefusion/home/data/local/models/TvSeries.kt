package com.example.framefusion.home.data.local.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.framefusion.home.data.local.converters.BackdropConverter
import com.example.framefusion.home.data.local.converters.GenreListConverter
import com.example.framefusion.home.data.local.converters.PersonListConverter
import com.example.framefusion.home.data.local.converters.PosterConverter
import com.example.framefusion.home.data.local.converters.RatingConverter

@Entity(tableName = "tv_series")
@TypeConverters(
    RatingConverter::class,
    PosterConverter::class,
    BackdropConverter::class,
    GenreListConverter::class,
    PersonListConverter::class
)
data class TvSeries(
    @PrimaryKey
    val id: Int?,
    val name: String?,
    val year: Int?,
    val shortDescription: String?,
    val rating: Rating,
    val seriesLength: String?,
    val totalSeriesLength: String?,
    val poster: Poster,
    val backdrop: Backdrop,
    val genres: List<Genre>,
    val persons: List<Person>,
    val top250: Int?
)