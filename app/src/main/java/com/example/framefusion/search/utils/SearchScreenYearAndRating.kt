package com.example.framefusion.search.utils

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.framefusion.itemDetails.utils.converters.ratingColor
import com.example.framefusion.search.data.local.models.SearchItem

@Composable
internal fun SearchScreenYearAndRating(
    searchItem: SearchItem,
    ratingKp: Double?
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
    ) {
        Text(text = searchItem.year ?: "")
        if (searchItem.rating?.kp != null && ratingKp?.toFloat() != 0.0f) {
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