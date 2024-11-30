package com.example.framefusion.itemDetails.presentation

import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import com.example.framefusion.itemDetails.utils.itemDetailsFullCastScreen.ItemDetailsFullCastScreenContent
import com.example.framefusion.utils.Navigator
import com.example.framefusion.utils.composable.SimpleTopAppBar

@Composable
fun FullItemCastScreen(
    navigator: Navigator
) {
    Scaffold(
        topBar = {
            SimpleTopAppBar(navigator)
        },
        content = { paddingValues ->
            ItemDetailsFullCastScreenContent(paddingValues, navigator)
        }
    )
}