package com.example.framefusion.search.data.local.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.framefusion.home.data.local.converters.GenreListConverterForMovies
import com.example.framefusion.home.data.local.converters.PosterConverter
import com.example.framefusion.home.data.local.models.Genre
import com.example.framefusion.home.data.local.models.Poster
import com.example.framefusion.itemDetails.data.local.convertes.RatingConverter
import com.example.framefusion.itemDetails.data.local.models.Rating

@Entity(tableName = "search_item")
@TypeConverters(
    PosterConverter::class,
    RatingConverter::class,
    GenreListConverterForMovies::class,
)
data class SearchItem(
    @PrimaryKey(autoGenerate = false)
    val displayId: Int ?= 0,
    val id: Int? = 0,
    val isFavorite: Boolean? = false,
    val type: String? = "",
    val name: String? = "",
    val year: String ?= "",
    val genres: List<Genre>?,
    val movieLength: String? = "",
    val seriesLength: String? = "",
    val totalSeriesLength: String? = "",
    val rating: Rating?,
    val shortDescription: String? = "",
    val description: String? = "",
    val poster: Poster?
)