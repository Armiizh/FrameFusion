package com.example.framefusion.features.itemDetails.utils.actorDetailsScreen

import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import com.example.framefusion.features.itemDetails.utils.itemDetailsScreen.ChangeFavoriteStatusButton
import com.example.framefusion.utils.Constants.Colors.gradientColors
import com.example.framefusion.utils.Constants.Colors.transparentColors

@Composable
fun FavoriteToggle(isFavorite: Boolean, onClick: () -> Unit) {

    val progress by animateFloatAsState(
        targetValue = if (isFavorite) 1f else 0f,
        animationSpec = spring(
            dampingRatio = Spring.DampingRatioMediumBouncy,
            stiffness = Spring.StiffnessLow
        ), label = ""
    )

    val offsetY by animateDpAsState(
        targetValue = if (isFavorite) 64.dp else 0.dp,
        animationSpec = spring(
            dampingRatio = Spring.DampingRatioMediumBouncy,
            stiffness = Spring.StiffnessLow
        ),
        label = ""
    )

    var buttonSize by remember { mutableStateOf(IntSize.Zero) }

    // Кисть для фона
    val backgroundBrush = remember(progress, buttonSize) {
        if (isFavorite) {
            Brush.verticalGradient(
                colors = gradientColors,
                startY = -buttonSize.height.toFloat() * (1 - progress),
                endY = buttonSize.height.toFloat() * progress
            )
        } else {
            Brush.verticalGradient(
                colors = transparentColors
            )
        }
    }

    val borderBrush = remember(progress, buttonSize) {
        if (!isFavorite) {
            Brush.verticalGradient(
                colors = gradientColors,
            )
        } else {
            Brush.verticalGradient(
                colors = listOf(Color.Transparent, Color.Transparent)
            )
        }
    }

    Box(
        modifier = Modifier
            .height(128.dp)
            .onSizeChanged { buttonSize = it }
            .clip(RoundedCornerShape(32.dp))
            .background(brush = backgroundBrush, shape = RoundedCornerShape(32.dp))
            .border(4.dp, borderBrush, RoundedCornerShape(32.dp)),
        contentAlignment = Alignment.TopCenter
    ) {
        // Используем смещение для кнопки
        ChangeFavoriteStatusButton(
            modifier = Modifier.offset(y = offsetY),
            isLiked = isFavorite,
            onClick = onClick
        )
    }
}