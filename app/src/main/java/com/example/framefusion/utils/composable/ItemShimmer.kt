package com.example.framefusion.utils.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun ItemShimmer() {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .width(165.dp)
            .height(225.5.dp)
            .clip(RoundedCornerShape(12.dp))
            .background(color = Color.LightGray)
    ) {}
}