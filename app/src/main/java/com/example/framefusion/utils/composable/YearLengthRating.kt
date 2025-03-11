package com.example.framefusion.utils.composable

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp
import com.example.framefusion.features.itemDetails.utils.converters.minutesToHoursAndMinutes
import com.example.framefusion.features.itemDetails.utils.converters.ratingColor
import java.util.Locale

@Composable
fun YearLengthRating(
    year: String?,
    movieLength: String?,
    totalSeriesLength: String?,
    seriesLength: String?,
    type: String?,
    rating: Double?
) {
    Row(
        modifier = Modifier.fillMaxWidth()
    ) {
        val yearText =
            if (year != null && year != "null" && year != "") {
                "$year"
            } else null

        val movieLengthText =
            if (movieLength != null && movieLength != "null" && movieLength != "") {
                minutesToHoursAndMinutes(movieLength.toIntOrNull())
            } else null

        val totalLengthText =
            if (totalSeriesLength != null && totalSeriesLength != "null" && totalSeriesLength != "") {
                minutesToHoursAndMinutes(totalSeriesLength.toIntOrNull())
            } else null

        val seriesLengthText =
            if (seriesLength != null && seriesLength != "null" && seriesLength != "") {
                minutesToHoursAndMinutes(seriesLength.toIntOrNull())
            } else null

        val ratingText = if (rating != null && rating.toFloat() != 0.0f) {
            "%.1f".format(Locale.US, rating)
        } else null

        val displayText = when (type) {
            "movie" -> {
                listOfNotNull(
                    yearText,
                    movieLengthText,
                    ratingText
                ).joinToString(" * ")
            }

            "tv-series" -> {
                listOfNotNull(
                    yearText,
                    totalLengthText,
                    seriesLengthText,
                    ratingText
                ).joinToString(" * ")
            }

            else -> yearText
        }

        if (displayText != null) {
            val otherText =
                displayText.split(" * ").filter { it != ratingText }.joinToString(" * ")
            Text(
                text = "$otherText * ",
                fontSize = 16.sp,
                color = Color.White.copy(.5f)
            )
        }

        if (ratingText != null) {
            Text(
                text = ratingText,
                color = ratingColor(rating),
                fontSize = 16.sp
            )
        }
    }
}