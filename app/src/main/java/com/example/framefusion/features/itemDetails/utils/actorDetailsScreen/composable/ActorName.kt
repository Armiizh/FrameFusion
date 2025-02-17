package com.example.framefusion.features.itemDetails.utils.actorDetailsScreen.composable

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ActorName(name: String?, enName: String?) {
    Row(
        Modifier
            .fillMaxWidth()
            .padding(top = 12.dp), horizontalArrangement = Arrangement.Center) {
        if (name != null) {
            Text(
                text = name,
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp,
                textAlign = TextAlign.Center
            )
        } else if (enName != null) {
            Text(
                text = enName,
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp,
                textAlign = TextAlign.Center
            )
        } else {
            Text(
                text = "Без имени",
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp,
                textAlign = TextAlign.Center
            )
        }
    }
}