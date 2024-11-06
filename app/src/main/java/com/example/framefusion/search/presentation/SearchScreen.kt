package com.example.framefusion.search.presentation

import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.example.framefusion.R
import com.example.framefusion.home.utils.homeScreen.content.TopAppBarContent
import com.example.framefusion.search.SearchItemViewModel
import com.example.framefusion.search.utils.content.SearchScreenContent

@Composable
fun SearchScreen(
    viewModel: SearchItemViewModel,
    onItemDetailsScreen: (Int?) -> Unit
) {
    Scaffold(
        topBar = { TopAppBarContent(stringResource(R.string.FindScreen)) },
        content = { paddingValues ->
            SearchScreenContent(paddingValues, viewModel, onItemDetailsScreen)
        }
    )
}