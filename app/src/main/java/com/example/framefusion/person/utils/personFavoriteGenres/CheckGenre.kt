package com.example.framefusion.person.utils.personFavoriteGenres

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun CheckGenre(
    nameItem: String,
    place: MutableState<Boolean>
) {
    val itemColor = animateColorAsState(
        targetValue = if (place.value) {
            MaterialTheme.colorScheme.secondary
        } else {
            Color.Transparent.copy(alpha = .2f)
        },
        animationSpec = tween(durationMillis = 300), label = ""
    )
    Box(
        modifier = Modifier
            .padding(8.dp)
            .clip(RoundedCornerShape(12.dp))
            .background(color = itemColor.value)
            .clickable { place.value = !place.value },
        contentAlignment = Alignment.Center
    ) {
        Text(
            modifier = Modifier
                .padding(vertical = 24.dp)
                .padding(start = 8.dp),
            text = nameItem,
            fontSize = 18.sp,
            color = MaterialTheme.colorScheme.onBackground
        )
    }
}