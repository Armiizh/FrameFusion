package com.example.framefusion.search.utils.composable

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.framefusion.R

@Composable
fun NoImage() {
    Image(
        modifier = Modifier
            .padding(end = 16.dp)
            .clip(RoundedCornerShape(12.dp))
            .height(200.dp),
        painter = painterResource(id = R.drawable.enoughtposter),
        contentDescription = null,
        contentScale = ContentScale.Crop,
    )
}