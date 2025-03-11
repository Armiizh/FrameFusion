package com.example.framefusion.features.itemDetails.utils.itemDetailsScreen

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.SpringSpec
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DividerDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.framefusion.R

@Composable
fun ItemDetailsDescription(
    description: String?,
    shortDescription: String?
) {
    val maxLines by remember { mutableIntStateOf(4) }
    var linesCount by remember { mutableIntStateOf(maxLines) }
    var isExpanded by remember { mutableStateOf(false) }

    Text(
        text = stringResource(id = R.string.Description),
        fontSize = 18.sp,
        color = Color.White.copy(.8f)
    )

    HorizontalDivider(
        thickness = DividerDefaults.Thickness,
        modifier = Modifier
            .height(1.dp)
            .fillMaxWidth()
    )

    Spacer(modifier = Modifier.height(12.dp))

    // Кисть для фона
    val textBrush =
        if (isExpanded || linesCount < maxLines) {
            Brush.linearGradient(
                colors = listOf(
                    Color.White.copy(alpha = .5f),
                    Color.White.copy(alpha = .5f)
                )
            )
        } else {
            Brush.verticalGradient(
                colors = listOf(
                    Color.White.copy(alpha = .5f),
                    Color.Transparent
                )
            )
        }


    val textDescription =
        if (description != null && description != "" && description != "null") {
            "$description"
        } else if (shortDescription != null && shortDescription != "" && shortDescription != "null") {
            "$shortDescription"
        } else {
            stringResource(R.string.Empty_description)
        }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 12.dp)
            .animateContentSize(
                remember {
                    SpringSpec(
                        dampingRatio = Spring.DampingRatioMediumBouncy,
                        stiffness = Spring.StiffnessLow
                    )
                }
            )
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = null,
                onClick = { isExpanded = !isExpanded }
            ),
        horizontalAlignment = Alignment.Start
    ) {
        Text(
            softWrap = true,
            text = textDescription,
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Justify,
            maxLines = if (isExpanded) Int.MAX_VALUE else maxLines,
            onTextLayout = { linesCount = it.lineCount },
            style = TextStyle(brush = textBrush)
        )
    }
}