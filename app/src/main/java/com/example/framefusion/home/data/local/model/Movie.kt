package com.example.framefusion.home.data.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.framefusion.home.data.local.converters.BackdropConverter
import com.example.framefusion.home.data.local.converters.GenreListConverter
import com.example.framefusion.home.data.local.converters.PersonListConverter
import com.example.framefusion.home.data.local.converters.PosterConverter
import com.example.framefusion.home.data.local.converters.RatingConverter

@Entity(tableName = "movie")
@TypeConverters(
    RatingConverter::class,
    PosterConverter::class,
    BackdropConverter::class,
    GenreListConverter::class,
    PersonListConverter::class
)
data class Movie(
    @PrimaryKey
    val id: Int?,
    val name: String?,
    val year: Int?,
    val shortDescription: String?,
    val rating: Rating,
    val movieLength: String?,
    val poster: Poster,
    val backdrop: Backdrop,
    val genres: List<Genre>,
    val persons: List<Person>
)

data class Rating(
    val kp: Double?,
    val imdb: Double?,
    val filmCritics: Double?,
    val russianFilmCritics: Double?,
    val await: Double?
)

data class Poster(
    val url: String?,
    val previewUrl: String?
)

data class Backdrop(
    val url: String?,
    val previewUrl: String?
)

data class Genre(
    val name: String?
)

data class Person(
    val id: Int,
    val photo: String,
    val name: String,
    val enName: String,
    val description: String?,
    val profession: String,
    val enProfession: String
)