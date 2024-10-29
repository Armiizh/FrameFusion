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
    val isLiked: Boolean? = false,
    val id: Int? = 0,
    val name: String? = "",
    val poster: Poster?,
    val description: String ?= "",
    val shortDescription: String ?= "",
    val year: String ?= "",
    val genres: List<Genre>?,
    val rating: Rating?,
    val type: String ?= ""
)