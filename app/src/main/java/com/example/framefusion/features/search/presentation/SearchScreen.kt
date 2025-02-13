package com.example.framefusion.features.search.presentation

import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.example.framefusion.R
import com.example.framefusion.features.home.utils.homeScreen.content.TopAppBarContent
import com.example.framefusion.features.search.utils.content.SearchScreenContent
import com.example.framefusion.utils.navigation.Navigator

@Composable
fun SearchScreen(
    navigator: Navigator
) {
    Scaffold(
        topBar = { TopAppBarContent(stringResource(R.string.FindScreen)) },
        content = { paddingValues ->
            SearchScreenContent(paddingValues) { id ->
                navigator.navigateToItemDetails(id)
            }
        }
    )
}