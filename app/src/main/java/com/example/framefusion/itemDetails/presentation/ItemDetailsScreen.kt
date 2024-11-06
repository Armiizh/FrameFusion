package com.example.framefusion.itemDetails.presentation

import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.NavHostController
import com.example.framefusion.itemDetails.DetailsScreenViewModel
import com.example.framefusion.itemDetails.data.local.models.ItemDetails
import com.example.framefusion.itemDetails.utils.itemDetailsScreen.content.ItemDetailsScreenContent

@Composable
fun ItemDetailsScreen(
    navController: NavHostController,
    viewModel: DetailsScreenViewModel,
    onFullCastScreen: () -> Unit,
    changeStatus: (ItemDetails, Boolean) -> Unit
) {
    val isItemLoading by viewModel.isItemLoading.collectAsState()
    Scaffold(
        content = { paddingValues ->
            ItemDetailsScreenContent(
                paddingValues,
                isItemLoading,
                viewModel,
                navController,
                onFullCastScreen,
                changeStatus
            )
        }
    )
}