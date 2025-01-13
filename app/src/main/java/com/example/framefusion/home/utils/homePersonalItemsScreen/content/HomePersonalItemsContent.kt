package com.example.framefusion.home.utils.homePersonalItemsScreen.content

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import com.example.framefusion.home.HomeScreenViewModel
import com.example.framefusion.home.utils.homePersonalItemsScreen.PersonalItemsContent
import com.example.framefusion.utils.navigation.Navigator
import com.example.framefusion.utils.ui.Background
import com.example.framefusion.utils.ui.FrameFusionColumn


@Composable
fun HomePersonalItemsContent(
    paddingValues: PaddingValues,
    navigator: Navigator,
    type: String?,
    viewModel: HomeScreenViewModel,
    onTypeChange: (String) -> Unit
) {
    Background()
    FrameFusionColumn(paddingValues) {
        PersonalItemsContent(navigator, type, viewModel, onTypeChange)
    }
}