package com.example.framefusion.itemDetails.data.local.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.framefusion.home.data.local.converters.PosterConverter
import com.example.framefusion.home.data.local.models.Genre
import com.example.framefusion.home.data.local.models.Poster
import com.example.framefusion.itemDetails.data.local.convertes.BackdropConverter
import com.example.framefusion.itemDetails.data.local.convertes.GenreListConverter
import com.example.framefusion.itemDetails.data.local.convertes.PersonListConverter
import com.example.framefusion.itemDetails.data.local.convertes.RatingConverter

@Entity(tableName = "item_details")
@TypeConverters(
    PosterConverter::class,
    GenreListConverter::class,
    BackdropConverter::class,
    RatingConverter::class,
    PersonListConverter::class
)
data class ItemDetails(
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
    val rating: Rating,
    val shortDescription: String? = "",
    val description: String? = "",
    val persons: List<Person>,
    val poster: Poster,
    val backdrop: Backdrop,
)