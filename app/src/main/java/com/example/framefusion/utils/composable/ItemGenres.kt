package com.example.framefusion.utils.composable

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.example.framefusion.features.home.data.local.models.Genre
import com.example.framefusion.features.itemDetails.utils.converters.genreFormatted

@Composable
fun ItemGenres(
    genres: List<Genre>?
) {
    if (genres != null) {
        val detailsGenres = genreFormatted(genres)
        Text(
            text = detailsGenres,
            color = Color.White.copy(.5f)
        )
    }
}