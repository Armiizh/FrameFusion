package com.example.framefusion.search.utils.content

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.example.framefusion.search.SearchItemViewModel
import com.example.framefusion.search.utils.SearchItems

@Composable
fun SearchContent(
    viewModel: SearchItemViewModel,
    onItemDetailsScreen: (Int?) -> Unit
) {
    val searchItem by viewModel.itemSearch.collectAsState()
    LazyColumn {
        items(searchItem) { searchItem ->
            SearchItems(searchItem, onItemDetailsScreen)
        }
    }
}