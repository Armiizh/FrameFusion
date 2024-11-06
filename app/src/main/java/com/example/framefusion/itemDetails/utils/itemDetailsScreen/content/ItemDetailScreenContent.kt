package com.example.framefusion.itemDetails.utils.itemDetailsScreen.content

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.framefusion.itemDetails.DetailsScreenViewModel
import com.example.framefusion.itemDetails.data.local.models.ItemDetails
import com.example.framefusion.itemDetails.utils.itemDetailsScreen.DetailsScreenShimmer
import com.example.framefusion.itemDetails.utils.itemDetailsScreen.ItemDetailsBackdrop
import com.example.framefusion.utils.ui.Background

@Composable
fun ItemDetailsScreenContent(
    paddingValues: PaddingValues,
    isItemLoading: Boolean,
    viewModel: DetailsScreenViewModel,
    navController: NavHostController,
    onFullCastScreen: () -> Unit,
    changeStatus: (ItemDetails, Boolean) -> Unit
) {
    Background()
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(paddingValues)
            .padding(bottom = 80.dp)
    ) {
        if (isItemLoading) {
            DetailsScreenShimmer()
        } else {
            ItemDetailsBackdrop(viewModel, changeStatus, navController)
            ItemDetailsContent(viewModel, onFullCastScreen)
        }
    }
}