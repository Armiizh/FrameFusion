package com.example.framefusion.utils.composable

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp

@Composable
fun ItemName(
    name: String?,
    textAlign: TextAlign = TextAlign.Center
) {
    if (name != null && name != "null" && name != "") {
        Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
            Text(
                textAlign = textAlign,
                text = name,
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}