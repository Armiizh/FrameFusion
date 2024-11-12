package com.example.framefusion.utils.composable

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
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
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.valentinilk.shimmer.shimmer

@Composable
fun Poster(url: String?, modifier: Modifier = Modifier, onClick: () -> Unit = {}) {
    if (url != null && url != "null" && url != "") {
        Box(
            modifier = modifier
                .width(165.dp)
                .height(225.5.dp)
                .padding(end = 12.dp, bottom = 12.dp),
            contentAlignment = Alignment.Center
        ) {
            val density = LocalDensity.current
            val widthPx = with(density) { 165.dp.toPx() }
            val heightPx = with(density) { 225.5.dp.toPx() }
            var isLoading by remember { mutableStateOf(false) }
            AsyncImage(
                modifier = Modifier
                    .fillMaxSize()
                    .clip(RoundedCornerShape(12.dp))
                    .clickable { onClick() },
                model = ImageRequest
                    .Builder(LocalContext.current)
                    .data(url)
                    .size(widthPx.toInt(), heightPx.toInt())
                    .crossfade(true)
                    .build(),
                contentDescription = null,
                contentScale = ContentScale.FillBounds,
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
                Box(
                    modifier = Modifier
                        .wrapContentSize()
                        .shimmer(),
                    contentAlignment = Alignment.Center
                ) {
                    ItemShimmer()
                }
            }
        }
    } else {
        Box(
            modifier = Modifier
                .width(165.dp)
                .height(225.5.dp)
                .padding(end = 12.dp, bottom = 12.dp),
            contentAlignment = Alignment.Center
        ) {
            NoImage()
        }
    }
}