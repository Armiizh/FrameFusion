package com.example.framefusion.itemDetails.utils.composable

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.framefusion.NavRoute

@Composable
fun IconBack(navController: NavHostController) {
    Icon(
        modifier = Modifier
            .clickable { navController.popBackStack() }
            .padding(12.dp),
        imageVector = Icons.Default.ArrowBack,
        contentDescription = null,
        tint = MaterialTheme.colorScheme.onBackground
    )
}