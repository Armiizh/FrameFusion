package com.example.framefusion.utils.composable

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavHostController

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun SimpleTopAppBar(navController: NavHostController) {
    TopAppBar(
        title = {},
        navigationIcon = { IconBack(navController) },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Color.Transparent
        )
    )
}