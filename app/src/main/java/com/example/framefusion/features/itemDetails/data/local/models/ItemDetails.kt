package com.example.framefusion.features.itemDetails.data.local.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.framefusion.features.home.data.local.models.Genre
import com.example.framefusion.features.home.data.local.models.Poster

@Entity(tableName = "item_details")
data class ItemDetails(
    @PrimaryKey
    @ColumnInfo("id")
    val id: Int? = 0,
    @ColumnInfo("isFavorite")
    val isFavorite: Boolean = false,
    @ColumnInfo("type")
    val type: String? = "",
    @ColumnInfo("name")
    val name: String? = "",
    @ColumnInfo("year")
    val year: String? = "",
    @ColumnInfo("genres")
    val genres: List<Genre>,
    @ColumnInfo("movieLength")
    val movieLength: String? = "",
    @ColumnInfo("seriesLength")
    val seriesLength: String? = "",
    @ColumnInfo("totalSeriesLength")
    val totalSeriesLength: String? = "",
    @ColumnInfo("rating")
    val rating: Rating,
    @ColumnInfo("shortDescription")
    val shortDescription: String? = "",
    @ColumnInfo("description")
    val description: String? = "",
    @ColumnInfo("persons")
    val persons: List<Person>,
    @ColumnInfo("poster")
    val poster: Poster,
    @ColumnInfo("backdrop")
    val backdrop: Backdrop,
)