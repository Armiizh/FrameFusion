package com.example.framefusion.home.utils.composable

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import coil.size.Size
import com.example.framefusion.home.data.local.models.Top10PersonalMovie

@Composable
internal fun MovieItem(top10PersonalMovie: Top10PersonalMovie, provideId: (Int?) -> Unit) {
    var isLoading by remember { mutableStateOf(false) }
    AsyncImage(
        modifier = Modifier
            .fillMaxWidth()
            .padding(end = 12.dp)
            .clip(RoundedCornerShape(12.dp))
            .clickable { provideId(top10PersonalMovie.id) },
        model = ImageRequest
            .Builder(LocalContext.current)
            .data(top10PersonalMovie.poster.url)
            .size(Size.ORIGINAL)
            .crossfade(true)
            .build(),
        contentDescription = null,
        onLoading = {
            isLoading = true
        },
        onError = {
            isLoading = false
        },
        onSuccess = {
            isLoading = false
        }
    )
    if (isLoading) {
        ItemShimmer()
    }
}