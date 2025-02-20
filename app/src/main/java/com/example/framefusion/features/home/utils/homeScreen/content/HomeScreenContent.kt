package com.example.framefusion.features.home.utils.homeScreen.content

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.material3.pulltorefresh.rememberPullToRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.example.framefusion.features.home.HomeScreenViewModel
import com.example.framefusion.features.home.utils.homeScreen.HomeTop10PersonalContent
import com.example.framefusion.utils.Constants.MOVIES
import com.example.framefusion.utils.Constants.TV_SERIES
import com.example.framefusion.utils.composable.Poster
import com.example.framefusion.utils.navigation.Navigator
import com.example.framefusion.utils.ui.Background
import com.example.framefusion.utils.ui.FrameFusionColumn

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreenContent(
    paddingValues: PaddingValues,
    navigator: Navigator,
    homeScreenViewModel: HomeScreenViewModel
) {

    val isRefreshing by homeScreenViewModel.isRefreshing.collectAsState()
    val pullToRefreshState = rememberPullToRefreshState()

    LaunchedEffect(Unit) {
        homeScreenViewModel.onRetry(false)
    }

    Background()
    PullToRefreshBox(
        isRefreshing = isRefreshing,
        onRefresh = { homeScreenViewModel.onRetry() },
        state = pullToRefreshState
    ) {
        FrameFusionColumn(paddingValues) {
            //Фильмы
            val movies by homeScreenViewModel.top10PersonalMovies.collectAsState()

            HomeTop10PersonalContent(
                type = MOVIES,
                result = movies,
                onHomePersonalItemsScreen = {
                    navigator.navigateToHomeMore("movie")
                },
                itemContent = { movieItem ->
                    Poster(movieItem.poster.url) {
                        navigator.navigateToItemDetails(movieItem.id)
                    }
                },
                onRetry = { homeScreenViewModel.onRetry() }
            )

            //Сериалы
            val tvSeries by homeScreenViewModel.top10PersonalTvSeries.collectAsState()

            HomeTop10PersonalContent(
                type = TV_SERIES,
                result = tvSeries,
                onHomePersonalItemsScreen = {
                    navigator.navigateToHomeMore("tv-series")
                },
                itemContent = { tvSeriesItem ->
                    Poster(tvSeriesItem.poster.url) {
                        navigator.navigateToItemDetails(tvSeriesItem.id)
                    }
                },
                onRetry = { homeScreenViewModel.onRetry() }
            )
        }
    }
}