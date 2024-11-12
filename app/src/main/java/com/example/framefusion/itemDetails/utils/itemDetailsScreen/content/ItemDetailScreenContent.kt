package com.example.framefusion.itemDetails.utils.itemDetailsScreen.content

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.example.framefusion.itemDetails.DetailsScreenViewModel
import com.example.framefusion.itemDetails.data.local.models.ItemDetails
import com.example.framefusion.itemDetails.utils.itemDetailsScreen.DetailsScreenShimmer
import com.example.framefusion.itemDetails.utils.itemDetailsScreen.ItemDetailsBackdrop
import com.example.framefusion.utils.ui.Background
import com.example.framefusion.utils.ui.FrameFusionColumn

@Composable
fun ItemDetailsScreenContent(
    paddingValues: PaddingValues,
    isItemLoading: Boolean,
    viewModel: DetailsScreenViewModel,
    navController: NavHostController,
    onFullCastScreen: () -> Unit,
    changeStatus: (ItemDetails, Boolean) -> Unit,
    onActorDetailsScreen: (Int?) -> Unit
) {
    Background()

    FrameFusionColumn(paddingValues) {
        if (isItemLoading) {
            DetailsScreenShimmer()
        } else {
            LazyColumn {
                item {
                    ItemDetailsBackdrop(viewModel, changeStatus, navController)
                }
            }
            ItemDetailsContent(viewModel, onFullCastScreen, onActorDetailsScreen)
        }
    }
}