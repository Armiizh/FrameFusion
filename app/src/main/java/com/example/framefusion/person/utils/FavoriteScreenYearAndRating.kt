package com.example.framefusion.person.utils

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.framefusion.itemDetails.utils.converters.ratingColor
import com.example.framefusion.person.data.model.FavoriteItem

@Composable
internal fun FavoriteScreenYearAndRating(
    favoriteItem: FavoriteItem,
    ratingKp: Double?
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
    ) {
        Text(text = favoriteItem.year ?: "")
        if (favoriteItem.rating.kp != null && ratingKp?.toFloat() != 0.0f) {
            Row {
                Text(text = " *")
                Text(
                    text = " $ratingKp",
                    color = ratingColor(ratingKp!!)
                )
            }
        }
    }
}