package com.example.framefusion.utils.composable

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import com.example.framefusion.features.home.data.local.models.Genre
import com.example.framefusion.features.itemDetails.utils.converters.genreFormatted

@Composable
fun ItemGenres(
    genres: List<Genre>?,
    textAlign: TextAlign = TextAlign.Center
) {
    if (genres != null) {
        val detailsGenres = genreFormatted(genres)
        Column(Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
            Text(
                textAlign = textAlign,
                text = detailsGenres
            )
        }
    }
}