package com.example.framefusion.itemDetails.presentation

import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.example.framefusion.itemDetails.DetailsScreenViewModel
import com.example.framefusion.itemDetails.utils.itemDetailsFullCastScreen.ItemDetailsFullCastScreenContent
import com.example.framefusion.utils.composable.SimpleTopAppBar

@Composable
fun FullItemCastScreen(
    navController: NavHostController,
    viewModel: DetailsScreenViewModel
) {
    Scaffold(
        topBar = {
            SimpleTopAppBar(navController)
        },
        content = { paddingValues ->
            ItemDetailsFullCastScreenContent(paddingValues, viewModel)
        }
    )
}