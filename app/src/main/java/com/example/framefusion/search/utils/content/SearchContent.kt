package com.example.framefusion.search.utils.content

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.example.framefusion.search.SearchItemViewModel
import com.example.framefusion.utils.composable.Item

@Composable
fun SearchContent(
    viewModel: SearchItemViewModel,
    onItemDetailsScreen: (Int?) -> Unit
) {
    val searchItem by viewModel.itemSearch.collectAsState()
    LazyColumn {
        items(searchItem) { searchItem ->
            Item(
                id = searchItem.id,
                posterUrl = searchItem.poster?.url,
                name = searchItem.name,
                genres = searchItem.genres,
                year = searchItem.year,
                movieLength = searchItem.movieLength,
                totalSeriesLength = searchItem.totalSeriesLength,
                seriesLength = searchItem.seriesLength,
                type = searchItem.type,
                rating = searchItem.rating?.kp,
                description = searchItem.description,
                shortDescription = searchItem.shortDescription
            ) { onItemDetailsScreen(searchItem.id) }
        }
    }
}