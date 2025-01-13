package com.example.framefusion.home.presentation

import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.example.framefusion.R
import com.example.framefusion.home.HomeScreenViewModel
import com.example.framefusion.home.utils.homeScreen.content.HomeScreenContent
import com.example.framefusion.home.utils.homeScreen.content.TopAppBarContent
import com.example.framefusion.utils.navigation.Navigator

@Composable
fun HomeScreen(
    navigator: Navigator,
    viewModel: HomeScreenViewModel
) {
    Scaffold(
        topBar = {
            TopAppBarContent(stringResource(R.string.HomeScreen))
        },
        content = { paddingValues ->
            HomeScreenContent(paddingValues, navigator, viewModel)
        }
    )
}