package com.example.framefusion.itemDetails.utils.composable

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp

@Composable
fun ItemName1(name: String, textAlign: TextAlign = TextAlign.Center) {
    Text(
        textAlign = textAlign,
        text = name,
        fontSize = 32.sp,
        fontWeight = FontWeight.Bold
    )
}