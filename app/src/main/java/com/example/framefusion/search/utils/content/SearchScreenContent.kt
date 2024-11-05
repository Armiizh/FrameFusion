package com.example.framefusion.search.utils.content

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewModelScope
import com.example.framefusion.search.SearchItemViewModel
import com.example.framefusion.utils.ui.Background
import kotlinx.coroutines.launch

@Composable
fun SearchScreenContent(
    paddingValues: PaddingValues,
    viewModel: SearchItemViewModel,
    onItemDetailsScreen: (Int?) -> Unit
) {
    Background()
    Column(
        modifier = Modifier
            .padding(paddingValues)
            .padding(horizontal = 16.dp)
            .padding(bottom = 80.dp)
            .fillMaxWidth(),
    ) {
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