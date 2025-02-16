package com.example.framefusion.features.home.presentation

import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.example.framefusion.features.home.HomeScreenViewModel
import com.example.framefusion.features.home.utils.homePersonalItemsScreen.content.HomePersonalItemsContent
import com.example.framefusion.features.home.utils.homePersonalItemsScreen.content.HomePersonalItemsTopAppBarContent
import com.example.framefusion.utils.navigation.Navigator

@Composable
fun HomePersonalItemsScreen(
    navigator: Navigator,
    type: String?,
    homeScreenViewModel: HomeScreenViewModel
) {
    var currentType by remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            HomePersonalItemsTopAppBarContent(currentType, navigator)
        },
        content = { paddingValues ->
            HomePersonalItemsContent(
                paddingValues,
                navigator,
                type,
                homeScreenViewModel
            ) { type ->
                currentType = type
            }
        }
    )
}

