package com.example.framefusion.features.itemDetails.utils.actorDetailsScreen.composable

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp

@Composable
fun ShortActorInfoItem(title: String?, subTitle: String) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val text = if (title != null && title != "null") title else "0"

        Text(
            text = text,
            fontWeight = FontWeight.Bold,
            fontSize = 14.sp,
            textAlign = TextAlign.Center
        )

        Text(
            text = subTitle,
            fontSize = 12.sp,
            color = Color.LightGray.copy(.5f),
            textAlign = TextAlign.Center
        )
    }
}