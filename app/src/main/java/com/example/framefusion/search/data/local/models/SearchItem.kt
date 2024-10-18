package com.example.framefusion.search.data.local.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.framefusion.home.data.local.converters.GenreListConverterForMovies
import com.example.framefusion.home.data.local.converters.PosterConverter
import com.example.framefusion.home.data.local.models.Genre
import com.example.framefusion.home.data.local.models.Poster
import com.example.framefusion.itemDetails.data.local.convertes.BackdropConverter
import com.example.framefusion.itemDetails.data.local.convertes.PersonListConverter
import com.example.framefusion.itemDetails.data.local.convertes.RatingConverter
import com.example.framefusion.itemDetails.data.local.models.Backdrop
import com.example.framefusion.itemDetails.data.local.models.Person
import com.example.framefusion.itemDetails.data.local.models.Rating
import com.example.framefusion.search.data.local.converters.CountryListConverter
import com.example.framefusion.search.data.local.converters.ExternalIdConverter
import com.example.framefusion.search.data.local.converters.LogoConverter
import com.example.framefusion.search.data.local.converters.NameListConverter
import com.example.framefusion.search.data.local.converters.ReleaseYearListConverter
import com.example.framefusion.search.data.local.converters.VotesConverter

@Entity(tableName = "search_item")
@TypeConverters(
    NameListConverter::class,
    ExternalIdConverter::class,
    LogoConverter::class,
    PosterConverter::class,
    BackdropConverter::class,
    RatingConverter::class,
    VotesConverter::class,
    GenreListConverterForMovies::class,
    CountryListConverter::class,
    ReleaseYearListConverter::class,
    PersonListConverter::class
)
data class SearchItem(
    @PrimaryKey(autoGenerate = false)
    val displayId: Int ?= 0,
    val id: Int? = 0,
    val name: String? = "",
    val alternativeName: String? = "",
    val enName: String? = "",
    val type: String ?= "",
    val year: String ?= "",
    val description: String ?= "",
    val shortDescription: String ?= "",
    val movieLength: String ?= "",
    val isSeries: Boolean?,
    val ticketsOnSale: Boolean?,
    val totalSeriesLength: String ?= "",
    val seriesLength: String ?= "",
    val ratingMpaa: String ?= "",
    val ageRating: Int ?= 0,
    val top10: Int ?= 0,
    val top250: Int ?= 0,
    val typeNumber: Int ?= 0,
    val status: String ?= "",
    val names: List<Name>?,
    val externalId: ExternalId?,
    val logo: Logo?,
    val poster: Poster?,
    val backdrop: Backdrop?,
    val rating: Rating?,
    val votes: Votes?,
    val genres: List<Genre>?,
    val countries: List<Country>?,
    val releaseYears: List<ReleaseYear>?,
    val persons: List<Person> = emptyList()
)