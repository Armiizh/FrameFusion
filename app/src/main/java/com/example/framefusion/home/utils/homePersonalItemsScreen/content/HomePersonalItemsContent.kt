package com.example.framefusion.home.utils.homePersonalItemsScreen.content

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import com.example.framefusion.home.HomeScreenViewModel
import com.example.framefusion.home.utils.homePersonalItemsScreen.PersonalItemsContent
import com.example.framefusion.utils.ui.Background
import com.example.framefusion.utils.ui.FrameFusionColumn

@Composable
fun HomePersonalItemsContent(
    paddingValues: PaddingValues,
    viewModel: HomeScreenViewModel,
    onItemDetailsScreen: (Int?) -> Unit,
    onTypeChange: (String) -> Unit
) {
    Background()

    FrameFusionColumn(paddingValues) {
        PersonalItemsContent(viewModel, onTypeChange, onItemDetailsScreen)
    }
}