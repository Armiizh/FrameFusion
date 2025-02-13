package com.example.framefusion.features.itemDetails.utils.itemDetailsScreen

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun ChangeFavoriteStatusButton(
    modifier: Modifier,
    isLiked: Boolean?,
    onClick: () -> Unit
) {
    val isLike = isLiked ?: false
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier.padding(16.dp)
    ) {
        Canvas(
            modifier = Modifier
                .size(40.dp)
                .clickable { onClick() }
        ) {
            drawCircle(
                color = if (isLike) Color.Red else Color.Gray,
                radius = size.minDimension / 2
            )
        }
        Icon(
            imageVector = if (isLike) Icons.Default.Favorite else Icons.Outlined.FavoriteBorder,
            contentDescription = "Like Button",
            tint = Color.White
        )
    }
}