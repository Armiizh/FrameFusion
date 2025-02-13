package com.example.framefusion.features.itemDetails.presentation

import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import com.example.framefusion.features.itemDetails.utils.itemDetailsFullCastScreen.ItemDetailsFullCastScreenContent
import com.example.framefusion.utils.composable.SimpleTopAppBar
import com.example.framefusion.utils.navigation.Navigator

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