package com.example.framefusion.features.home.utils.homeScreen.content

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.example.framefusion.utils.composable.NameOfScreen

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun TopAppBarContent(nameOfScreen: String) {
    TopAppBar(
        title = { NameOfScreen(nameOfScreen) },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Color.Transparent
        )
    )
}