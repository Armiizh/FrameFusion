package com.example.framefusion.home.presentation

import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import com.example.framefusion.home.HomeScreenViewModel
import com.example.framefusion.home.utils.homeScreen.content.HomeScreenContent
import com.example.framefusion.home.utils.homeScreen.content.HomeScreenTopAppBarContent

@Composable
fun HomeScreen(
    viewModel: HomeScreenViewModel,
    onItemDetailsScreen: (Int?) -> Unit,
    onHomePersonalItemsScreen: (String) -> Unit
) {
    Scaffold(
        topBar = {
            HomeScreenTopAppBarContent()
        },
        content = { paddingValues ->
            HomeScreenContent(
                paddingValues,
                viewModel,
                onItemDetailsScreen,
                onHomePersonalItemsScreen
            )
        }
    )
}