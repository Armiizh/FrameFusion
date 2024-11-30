package com.example.framefusion.itemDetails.utils.itemDetailsScreen.content

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.framefusion.itemDetails.DetailsScreenViewModel
import com.example.framefusion.itemDetails.utils.itemDetailsScreen.DetailsScreenShimmer
import com.example.framefusion.itemDetails.utils.itemDetailsScreen.ItemDetailsBackdrop
import com.example.framefusion.utils.Navigator
import com.example.framefusion.utils.ui.Background
import com.example.framefusion.utils.ui.FrameFusionColumn

@Composable
fun ItemDetailsScreenContent(
    paddingValues: PaddingValues,
    navigator: Navigator,
    viewModel: DetailsScreenViewModel = hiltViewModel()
) {
    val isItemLoading by viewModel.isItemLoading.collectAsStateWithLifecycle()

    Background()

    FrameFusionColumn(paddingValues) {

        if (asdas) {
            Text(text = "rere")
        } else {
            if (isItemLoading) {
                DetailsScreenShimmer()
            } else {
                LazyColumn {
                    item {
                        ItemDetailsBackdrop(viewModel, navigator)
                    }
                }
                ItemDetailsContent(viewModel, navigator)
            }
        }
    }
}