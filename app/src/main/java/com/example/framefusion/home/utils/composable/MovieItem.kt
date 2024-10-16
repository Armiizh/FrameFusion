package com.example.framefusion.home.utils.composable

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import coil.size.Size
import com.example.framefusion.home.data.local.models.Movie

@Composable
internal fun MovieItem(movie: Movie, provideId: (Int?) -> Unit) {
    AsyncImage(
        modifier = Modifier
            .fillMaxWidth()
            .padding(end = 12.dp)
            .clip(RoundedCornerShape(12.dp))
            .clickable { provideId(movie.id) },
        model = ImageRequest
            .Builder(LocalContext.current)
            .data(movie.poster.url)
            .size(Size.ORIGINAL)
            .crossfade(true)
            .build(),
        contentDescription = null,
    )
}