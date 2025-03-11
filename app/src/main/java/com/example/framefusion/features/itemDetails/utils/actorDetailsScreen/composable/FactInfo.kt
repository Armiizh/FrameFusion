package com.example.framefusion.features.itemDetails.utils.actorDetailsScreen.composable

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.framefusion.features.itemDetails.data.local.models.Facts


@Composable
fun FactInfo(
    facts: List<Facts>?
) {
    val listOfFacts = facts?.map { it.value }
    val maxLines = 4

    var isExpanded by remember { mutableStateOf(false) }
    var linesCount by remember { mutableIntStateOf(maxLines) }

    if (!listOfFacts.isNullOrEmpty()) {
        Column(
            modifier = Modifier
                .padding(horizontal = 12.dp)
                .fillMaxWidth()
                .clickable(
                    interactionSource = remember { MutableInteractionSource() },
                    indication = null,
                    onClick = { isExpanded = !isExpanded }
                )
        ) {
            val textBrush = if (isExpanded || linesCount < maxLines) {
                Brush.linearGradient(
                    colors = listOf(
                        Color.White.copy(alpha = .5f),
                        Color.White.copy(alpha = .5f)
                    )
                )
            } else {
                Brush.verticalGradient(
                    colors = listOf(Color.White.copy(alpha = .5f), Color.Transparent)
                )
            }

            Text(
                text = "А Вы знали, что...",
                modifier = Modifier.padding(vertical = 8.dp),
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )

            Text(
                text = listOfFacts.joinToString("\n\n"),
                color = Color.White.copy(alpha = 0.5f),
                fontSize = 14.sp,
                lineHeight = 20.sp,
                maxLines = if (isExpanded) Int.MAX_VALUE else maxLines,
                textAlign = TextAlign.Justify,
                onTextLayout = { linesCount = it.lineCount },
                modifier = Modifier
                    .fillMaxWidth(),
                style = TextStyle(
                    brush = textBrush
                )
            )
        }
    }
}