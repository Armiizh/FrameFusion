package com.example.framefusion.itemDetails.utils.composable

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import coil.size.Size

@Composable
fun Backdrop(url: String?) {
    if (url == null) {
        Spacer(modifier = Modifier.height(1.dp))
    } else {
        AsyncImage(
            modifier = Modifier
                .fillMaxWidth(),
            model = ImageRequest
                .Builder(LocalContext.current)
                .data(url)
                .size(Size.ORIGINAL)
                .crossfade(true)
                .build(),
            contentDescription = null,
        )
    }
}