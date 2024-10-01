package com.example.framefusion.home.utils

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
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
import com.example.framefusion.home.data.local.models.TvSeries

@Composable
internal fun TvSeriesItem(tvSeries: TvSeries) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(end = 12.dp)
    ) {
        Column(
            modifier = Modifier
                .clip(RoundedCornerShape(12.dp)),
            verticalArrangement = Arrangement.Center
        ) {
            AsyncImage(
                model = ImageRequest
                    .Builder(LocalContext.current)
                    .data(tvSeries.poster.url)
                    .size(coil.size.Size.ORIGINAL)
                    .build(),
                contentDescription = null
            )
        }
    }
}