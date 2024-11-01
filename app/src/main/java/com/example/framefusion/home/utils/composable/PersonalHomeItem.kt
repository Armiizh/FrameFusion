package com.example.framefusion.home.utils.composable

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.framefusion.search.utils.composable.placeholderPainter

@Composable
internal fun PersonalHomeItem(url: String, provideId: () -> Unit) {

    Box(
        modifier = Modifier
            .fillMaxWidth(0.5f)
            .padding(horizontal = 8.dp, vertical = 8.dp)
            .clickable { provideId() },
        contentAlignment = Alignment.Center
    ) {
        AsyncImage(
            modifier = Modifier.clip(RoundedCornerShape(8.dp)),
            model = ImageRequest
                .Builder(LocalContext.current)
                .data(url)
                .crossfade(true)
                .build(),
            contentScale = ContentScale.FillBounds,
            placeholder = placeholderPainter(),
            contentDescription = null,
        )
    }
}