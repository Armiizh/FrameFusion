package com.example.framefusion.features.itemDetails.utils.actorDetailsScreen.composable

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun SubscribeButton(
    isFavorite: Boolean,
    changeSubscribe: () -> Unit
) {
    val gradientColors = listOf(
        Color(0xFFBD2300),
        Color(0xFFE44500),
        Color(0xFFF47C00),
        Color(0xFFFFAC69),
        Color(0xFFFFE4D4),
    )

    val progress by animateFloatAsState(
        targetValue = if (isFavorite) 1f else 0f,
        animationSpec = tween(1000, easing = FastOutSlowInEasing), label = ""
    )

    var buttonSize by remember { mutableStateOf(IntSize.Zero) }

    // Кисть для фона
    val backgroundBrush = remember(progress, buttonSize) {
        if (isFavorite) {
            Brush.horizontalGradient(
                colors = gradientColors,
                startX = -buttonSize.width.toFloat() * (1 - progress),
                endX = buttonSize.width.toFloat() * progress
            )
        } else {
            Brush.linearGradient(colors = listOf(Color.Transparent, Color.Transparent))
        }
    }

    // Кисть для границы
    val borderBrush = remember(progress, buttonSize) {
        if (!isFavorite) {
            Brush.horizontalGradient(
                colors = gradientColors,
                startX = buttonSize.width.toFloat() * (1 - progress),
                endX = buttonSize.width.toFloat() * progress
            )
        } else {
            Brush.linearGradient(colors = listOf(Color.Transparent, Color.Transparent))
        }
    }

    Button(
        onClick = changeSubscribe,
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 12.dp, horizontal = 8.dp)
            .onSizeChanged { buttonSize = it }
            .clip(RoundedCornerShape(24.dp))
            .background(backgroundBrush, RoundedCornerShape(24.dp))
            .drawWithContent {
                drawContent()
                if (!isFavorite) {
                    drawRoundRect(
                        brush = borderBrush,
                        cornerRadius = CornerRadius(24.dp.toPx(), 24.dp.toPx()),
                        style = Stroke(width = 2.dp.toPx())
                    )
                }
            },
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.Transparent,
            contentColor = Color.White
        )
    ) {
        Text(
            text = if (isFavorite) "Unsubscribe" else "Subscribe",
            fontSize = 16.sp
        )
    }
}