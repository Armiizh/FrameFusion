package com.example.framefusion.home.utils.homePersonalItemsScreen

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.example.framefusion.home.HomeScreenViewModel

@Composable
fun PersonalItemsContent(
    viewModel: HomeScreenViewModel,
    onTypeChange: (String) -> Unit,
    onItemDetailsScreen: (Int?) -> Unit
) {
    val isItemLoading by viewModel.personalItemsLoading.collectAsState()
    if (isItemLoading) {
        HomePersonalItemsShimmer()
    } else {

        val items by viewModel.personalItems.collectAsState()
        var previousType by remember { mutableStateOf("") }
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            modifier = Modifier.fillMaxWidth()
        ) {
            items(items) { item ->
                if (item.poster.url != null && item.poster.url != "null" && item.poster.url != "") {
                    val url = item.poster.url
                    val currentType = item.type ?: ""
                    if (currentType != previousType) {
                        onTypeChange(currentType)
                        previousType = currentType
                    }
                    PersonalHomeItem(url) { onItemDetailsScreen(item.id) }
                }
            }
        }
    }
}