package com.example.framefusion.features.person.data.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.framefusion.features.home.data.local.converters.PosterConverter
import com.example.framefusion.features.home.data.local.models.Genre
import com.example.framefusion.features.home.data.local.models.Poster
import com.example.framefusion.features.itemDetails.data.local.convertes.GenreListConverter
import com.example.framefusion.features.itemDetails.data.local.convertes.RatingConverter
import com.example.framefusion.features.itemDetails.data.local.models.Rating

@Entity(tableName = "favorite_item")
@TypeConverters(
    PosterConverter::class,
    GenreListConverter::class,
    RatingConverter::class,
)
data class FavoriteItem(
    @PrimaryKey
    val id: Int? = 0,
    val isFavorite: Boolean? = false,
    val type: String? = "",
    val name: String? = "",
    val year: String? = "",
    val genres: List<Genre>,
    val movieLength: String? = "",
    val seriesLength: String? = "",
    val totalSeriesLength: String? = "",
    val rating: Rating?,
    val shortDescription: String? = "",
    val description: String? = "",
    val poster: Poster?
)