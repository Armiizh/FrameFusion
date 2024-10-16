package com.example.framefusion.itemDetails.utils.converters

import com.example.framefusion.home.data.local.models.Genre
import java.util.Locale

fun genreFormatted(genres: List<Genre>) =
    genres.joinToString(separator = " -") {
        it.name!!.split(" ").joinToString(" ") { word -> word.capitalize(Locale.ROOT) }
    }