package com.example.framefusion.person.utils.personScreen

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun MenuItem(
    name: String,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        onClick = onClick,
        colors = CardDefaults.cardColors(
            containerColor = Color.Transparent.copy(alpha = 0.3f),
            disabledContainerColor = Color.Transparent.copy(alpha = 0.3f)
        )
    ) {
        Text(
            modifier = Modifier.padding(24.dp),
            text = name,
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold
        )
    }
}