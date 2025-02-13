package com.example.framefusion.features.itemDetails.utils.converters

import androidx.compose.ui.graphics.Color

fun ratingColor(ratingKp: Double?): Color {
    if (ratingKp == null) {
        return Color.White
    } else {
        val color: Color = if (ratingKp >= 7.0f) {
            Color.Green
        } else if (ratingKp < 7.0f && ratingKp >= 4.0f) {
            Color.Yellow
        } else {
            Color.Red
        }
        return color
    }
}