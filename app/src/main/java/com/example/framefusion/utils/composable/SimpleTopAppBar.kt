package com.example.framefusion.utils.composable

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.example.framefusion.utils.Navigator

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun SimpleTopAppBar(navigator: Navigator) {
    TopAppBar(
        title = {},
        navigationIcon = { IconBack(navigator) },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Color.Transparent
        )
    )
}