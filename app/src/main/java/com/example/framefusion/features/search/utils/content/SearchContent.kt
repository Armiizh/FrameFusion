package com.example.framefusion.features.search.utils.content

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.example.framefusion.features.search.SearchItemViewModel
import com.example.framefusion.features.search.data.local.models.SearchItem
import com.example.framefusion.utils.composable.MoviePersonItem
import com.example.framefusion.utils.state.Result

@Composable
fun SearchContent(
    viewModel: SearchItemViewModel,
    onItemDetailsScreen: (Int?) -> Unit
) {
    val searchItems by viewModel.itemsSearch.collectAsState()
    when (searchItems) {
        is Result.Loading -> {
            Text("Result.Loading")
        }

        is Result.Error -> {
            Text("Result.Error")
        }

        is Result.Success -> {
            val items = (searchItems as Result.Success<List<SearchItem>>).data
            LazyColumn {
                items(items) { searchItem ->
                    MoviePersonItem(
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
    }
}