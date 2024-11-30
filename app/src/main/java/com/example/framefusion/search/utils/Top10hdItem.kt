package com.example.framefusion.search.utils

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.framefusion.search.data.local.models.Top10hd
import com.example.framefusion.utils.composable.Poster

@Composable
internal fun Top10hdItem(
    top10hd: Top10hd,
    onItemDetailsScreen: (Int?) -> Unit
) {
    Box(
        modifier = Modifier
            .width(200.dp)
            .height(250.dp)
    ) {
        Poster(
            top10hd.poster.url, Modifier.align(Alignment.TopEnd)
        ) { onItemDetailsScreen(top10hd.id) }
        DisplayIdText(top10hd, Modifier.align(Alignment.BottomStart))
    }
}

@Composable
private fun DisplayIdText(top10hd: Top10hd, modifier: Modifier = Modifier) {
    Text(
        modifier = modifier,
        text = "${top10hd.displayId}",
        fontSize = 100.sp,
        fontWeight = FontWeight.Bold,
        color = Color.White
    )
}