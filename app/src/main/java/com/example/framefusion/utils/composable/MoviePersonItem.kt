package com.example.framefusion.utils.composable

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.framefusion.features.home.data.local.models.Genre
import com.example.framefusion.features.search.utils.ItemDescription

@Composable
fun MoviePersonItem(
    id: Int?,
    posterUrl: String?,
    name: String?,
    genres: List<Genre>?,
    year: String?,
    movieLength: String?,
    totalSeriesLength: String?,
    seriesLength: String?,
    type: String?,
    rating: Double?,
    description: String?,
    shortDescription: String?,
    onItemDetailsScreen: (Int?) -> Unit
) {
    Column(Modifier.fillMaxWidth()) {
        Row(
            Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
                .clickable { onItemDetailsScreen(id) },
            horizontalArrangement = Arrangement.Start
        ) {
            Poster(posterUrl)
            Column(modifier = Modifier.fillMaxWidth(1f)) {
                ItemName(name)
                ItemType(type)
                ItemGenres(genres)
                YearLengthRating(
                    year,
                    movieLength,
                    totalSeriesLength,
                    seriesLength,
                    type,
                    rating
                )

                Spacer(modifier = Modifier.height(12.dp))

                ItemDescription(
                    description,
                    shortDescription
                )
            }
        }
        HorizontalDivider(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 12.dp),
            color = Color.LightGray.copy(alpha = .2f)
        )
    }
}