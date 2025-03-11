package com.example.framefusion.utils.composable

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

@Composable
fun ItemName(
    name: String?
) {
    if (name != null && name != "null" && name != "") {
        Text(
            text = name,
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold
        )
    }
}