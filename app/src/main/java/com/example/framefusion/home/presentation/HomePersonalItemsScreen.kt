package com.example.framefusion.home.presentation

import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.navigation.NavHostController
import com.example.framefusion.home.HomeScreenViewModel
import com.example.framefusion.home.utils.homePersonalItemsScreen.content.HomePersonalItemsContent
import com.example.framefusion.home.utils.homePersonalItemsScreen.content.HomePersonalItemsTopAppBarContent

@Composable
fun HomePersonalItemsScreen(
    viewModel: HomeScreenViewModel,
    navController: NavHostController,
    onItemDetailsScreen: (Int?) -> Unit
) {
    var currentType by remember { mutableStateOf("") }
    Scaffold(
        topBar = {
            HomePersonalItemsTopAppBarContent(currentType, navController)
        },
        content = { paddingValues ->
            HomePersonalItemsContent(
                paddingValues,
                viewModel,
                onItemDetailsScreen
            ) { type ->
                currentType = type
            }
        }
    )
}

