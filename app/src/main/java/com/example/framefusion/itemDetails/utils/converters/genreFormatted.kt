package com.example.framefusion.itemDetails.utils.converters

import com.example.framefusion.home.data.local.models.Genre
import java.util.Locale

fun genreFormatted(genres: List<Genre>): String {
    return genres.joinToString(separator = " -") { genre ->
        genre.name?.split(" ")?.joinToString(" ") { word ->
            word.replaceFirstChar {
                if (it.isLowerCase()) it.titlecase(
                    Locale.ROOT
                ) else it.toString()
            }
        } ?: ""
    }.trim()
}