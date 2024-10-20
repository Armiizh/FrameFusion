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
//    NameListConverter::class,
//    ExternalIdConverter::class,
//    LogoConverter::class,
    PosterConverter::class,
//    BackdropConverter::class,
    RatingConverter::class,
//    VotesConverter::class,
    GenreListConverterForMovies::class,
//    CountryListConverter::class,
//    ReleaseYearListConverter::class,
//    PersonListConverter::class
)
data class SearchItem(
    @PrimaryKey(autoGenerate = false)
    val displayId: Int ?= 0,
    val id: Int? = 0,
    val name: String? = "",
    val poster: Poster?,
    val description: String ?= "",
    val shortDescription: String ?= "",
    val year: String ?= "",
    val genres: List<Genre>?,
    val rating: Rating?,
    val type: String ?= "",

//    val alternativeName: String? = "",
//    val ratingMpaa: String ?= "",
//    val enName: String? = "",
//    val movieLength: String ?= "",
//    val isSeries: Boolean?,
//    val ticketsOnSale: Boolean?,
//    val totalSeriesLength: String ?= "",
//    val seriesLength: String ?= "",
//    val ageRating: Int ?= 0,
//    val top10: Int ?= 0,
//    val top250: Int ?= 0,
//    val typeNumber: Int ?= 0,
//    val status: String ?= "",
//    val names: List<Name>?,
//    val externalId: ExternalId?,
//    val logo: Logo?,
//    val backdrop: Backdrop?,

//    val votes: Votes?,

//    val countries: List<Country>?,
//    val releaseYears: List<ReleaseYear>?,
//    val persons: List<Person> = emptyList()
)