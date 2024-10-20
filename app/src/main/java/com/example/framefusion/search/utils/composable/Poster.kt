package com.example.framefusion.search.utils.composable

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import coil.size.Size
import com.example.framefusion.home.data.local.models.Poster

@Composable
fun Poster(url: String) {
    AsyncImage(
        modifier = Modifier
            .padding(end = 12.dp)
            .clip(RoundedCornerShape(12.dp)),
        model = ImageRequest
            .Builder(LocalContext.current)
            .data(url)
            .size(Size.ORIGINAL)
            .crossfade(true)
            .build(),
        contentDescription = null,
        contentScale = ContentScale.Crop
    )
}