package com.example.framefusion.home.utils.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.valentinilk.shimmer.shimmer

@Preview
@Composable
fun HomeScreenMovieShimmer() {

    LazyRow(
        modifier = Modifier
            .padding(start = 8.dp)
            .shimmer(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        items(3) {
            MovieItemShimmer()
        }
    }
}

@Composable
private fun MovieItemShimmer() {
    Box(
        modifier = Modifier
            .padding(horizontal = 8.dp)
            .height(210.dp)
            .width(160.dp)
            .clip(RoundedCornerShape(12.dp))
            .background(color = Color.LightGray)
    )
}