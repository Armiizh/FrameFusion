package com.example.framefusion.itemDetails.utils

import com.example.framefusion.itemDetails.data.local.models.MovieDetails
import java.util.Locale

fun genreFormatted(movieDetails: MovieDetails?) =
    movieDetails?.genres?.joinToString(separator = " -") {
        it.name!!.split(" ").joinToString(" ") { word -> word.capitalize(Locale.ROOT) }
    } ?: ""