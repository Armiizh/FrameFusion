package com.example.framefusion.search.presentation

import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import com.example.framefusion.search.SearchItemViewModel
import com.example.framefusion.search.utils.content.SearchScreenContent
import com.example.framefusion.search.utils.content.SearchScreenTopAppBarContent

@Composable
fun SearchScreen(
    viewModel: SearchItemViewModel,
    onItemDetailsScreen: (Int?) -> Unit
) {
    Scaffold(
        topBar = { SearchScreenTopAppBarContent() },
        content = { paddingValues ->
            SearchScreenContent(paddingValues, viewModel, onItemDetailsScreen)
        }
    )
}