package com.example.framefusion.utils.composable

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController

@Composable
fun IconBack(navController: NavHostController) {

    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .padding(16.dp)
    ) {
        Canvas(
            modifier = Modifier
                .size(40.dp)
                .clickable { navController.popBackStack() }
        ) {
            drawCircle(
                color = Color.Gray,
                radius = size.minDimension / 2
            )
        }
        Icon(
            imageVector = Icons.AutoMirrored.Outlined.ArrowBack,
            contentDescription = null,
            tint = MaterialTheme.colorScheme.onBackground,
        )
    }
}

