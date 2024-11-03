package com.example.framefusion.home.utils.composable

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.framefusion.R

@Composable
internal fun PersonalHomeItem(url: String, provideId: () -> Unit) {

    Box(
        modifier = Modifier
            .fillMaxWidth(0.5f)
            .padding(horizontal = 4.dp, vertical = 4.dp)
            .clickable { provideId() },
        contentAlignment = Alignment.Center
    ) {
        var isLoading by remember { mutableStateOf(false) }
        var hasError by remember { mutableStateOf(false) }
        AsyncImage(
            modifier = Modifier.clip(RoundedCornerShape(8.dp)),
            model = ImageRequest
                .Builder(LocalContext.current)
                .data(url)
                .crossfade(true)
                .build(),
            contentScale = ContentScale.FillBounds,
            contentDescription = null,
            onLoading = {
                isLoading = true
            },
            onError = {
                isLoading = false
                hasError = true
            },
            onSuccess = {
                isLoading = false
                hasError = false
            },
        )
        if (isLoading) {
            ItemShimmer()
        } else if (hasError) {
            Image(
                modifier = Modifier.fillMaxSize(),
                painter = painterResource(id = R.drawable.enoughtposter),
                contentDescription = null,
                contentScale = ContentScale.Crop
            )
        }
    }
}