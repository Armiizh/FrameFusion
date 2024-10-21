package com.example.framefusion.search.utils.composable

import androidx.compose.runtime.Composable
import androidx.compose.ui.text.style.TextAlign
import com.example.framefusion.home.data.local.models.Genre
import com.example.framefusion.itemDetails.utils.composable.ItemGenres

@Composable
internal fun SearchScreenGenres(
    genres: List<Genre>,
    detailsGenres: String
) {
    if (genres.isNotEmpty()) {
        ItemGenres(detailsGenres, textAlign = TextAlign.Start)
    }
}
