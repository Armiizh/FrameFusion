package com.example.framefusion.features.search.utils.content

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.material3.pulltorefresh.rememberPullToRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.framefusion.features.search.SearchItemViewModel
import com.example.framefusion.utils.ui.Background
import com.example.framefusion.utils.ui.FrameFusionColumn

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchScreenContent(
    paddingValues: PaddingValues,
    searchItemViewModel: SearchItemViewModel = hiltViewModel(),
    onItemDetailsScreen: (Int?) -> Unit
) {
    Background()

    val isRefreshing by searchItemViewModel.isRefreshing.collectAsState()
    val pullToRefreshState = rememberPullToRefreshState()

    PullToRefreshBox(
        isRefreshing = isRefreshing,
        onRefresh = { searchItemViewModel.onRetry() },
        state = pullToRefreshState
    ) {
        FrameFusionColumn(paddingValues) {

            var search by remember { mutableStateOf("") }

            SearchBarContent(search) { newSearch ->
                search = newSearch
                searchItemViewModel.searchData(newSearch)
            }
            if (search == "") {
                Top10hdContent(searchItemViewModel, onItemDetailsScreen)
            } else {
                SearchContent(searchItemViewModel, onItemDetailsScreen)
            }
        }
    }

}