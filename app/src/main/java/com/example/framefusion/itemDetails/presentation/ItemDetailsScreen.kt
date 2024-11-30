package com.example.framefusion.itemDetails.presentation

import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import com.example.framefusion.itemDetails.utils.itemDetailsScreen.content.ItemDetailsScreenContent
import com.example.framefusion.utils.Navigator

@Composable
fun ItemDetailsScreen(
    navigator: Navigator
) {
    Scaffold(
        content = { paddingValues ->
            ItemDetailsScreenContent(paddingValues, navigator)
        }
    )
}