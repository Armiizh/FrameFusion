package com.example.framefusion.itemDetails.utils.composable

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.style.TextAlign

@Composable
fun ItemGenres(
    detailsGenres: String,
    textAlign: TextAlign = TextAlign.Center
) {
    Text(
        textAlign = textAlign,
        text = detailsGenres
    )
}