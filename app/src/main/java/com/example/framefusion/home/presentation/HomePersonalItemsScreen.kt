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
import com.example.framefusion.utils.Navigator

@Composable
fun HomePersonalItemsScreen(
    navigator: Navigator
) {
    var currentType by remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            HomePersonalItemsTopAppBarContent(currentType, navigator)
        },
        content = { paddingValues ->
            HomePersonalItemsContent(
                paddingValues,
                navigator
            ) { type ->
                currentType = type
            }
        }
    )
}

