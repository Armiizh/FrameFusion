package com.example.framefusion.search.utils.content

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewModelScope
import com.example.framefusion.search.SearchItemViewModel
import com.example.framefusion.utils.ui.Background
import com.example.framefusion.utils.ui.FrameFusionColumn
import kotlinx.coroutines.launch

@Composable
fun SearchScreenContent(
    paddingValues: PaddingValues,
    onItemDetailsScreen: (Int?) -> Unit,
    viewModel: SearchItemViewModel = hiltViewModel()
) {
    Background()

    FrameFusionColumn(paddingValues) {
        var search by remember { mutableStateOf("") }
        SearchBarContent(search) { newSearch ->
            search = newSearch
            viewModel.viewModelScope.launch {
                if (newSearch.isEmpty()) {
                    viewModel.deleteSearch()
                } else {
                    viewModel.initData(newSearch)
                }
            }
        }
        if (search == "") {
            Top10hdContent(viewModel, onItemDetailsScreen)
        } else {
            SearchContent(viewModel, onItemDetailsScreen)
        }
    }
}