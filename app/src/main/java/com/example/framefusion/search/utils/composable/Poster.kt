package com.example.framefusion.search.utils.composable

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest

@Composable
internal fun Poster(url: String) {
    val width = with(LocalDensity.current) { 200.dp.toPx().toInt() }
    val height = with(LocalDensity.current) { 150.dp.toPx().toInt() }
    AsyncImage(
        modifier = Modifier
            .padding(end = 12.dp)
            .clip(RoundedCornerShape(12.dp)),
        model = ImageRequest
            .Builder(LocalContext.current)
            .data(url)
            .size(width, height)
            .crossfade(true)
            .build(),
        contentDescription = null,
        contentScale = ContentScale.Crop
    )
}