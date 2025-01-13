package com.example.framefusion.home.utils.homeScreen.content

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewModelScope
import com.example.framefusion.home.HomeScreenViewModel
import com.example.framefusion.home.utils.homeScreen.HomeTop10PersonalContent
import com.example.framefusion.utils.Constants.MOVIES
import com.example.framefusion.utils.Constants.TV_SERIES
import com.example.framefusion.utils.composable.Poster
import com.example.framefusion.utils.navigation.Navigator
import com.example.framefusion.utils.ui.Background
import com.example.framefusion.utils.ui.FrameFusionColumn
import kotlinx.coroutines.launch

@Composable
fun HomeScreenContent(
    paddingValues: PaddingValues,
    navigator: Navigator,
    viewModel: HomeScreenViewModel
) {

    LaunchedEffect(Unit) {
        viewModel.viewModelScope.launch {
            viewModel.initHomeTop10Personal()
        }
    }

    Background()
    FrameFusionColumn(paddingValues) {

        //Фильмы
        val movies by viewModel.top10PersonalMovies.collectAsState()

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
            onRetry = {
                viewModel.viewModelScope.launch {
                    viewModel.initHomeTop10Personal()
                }
            }
        )

        //Сериалы
        val tvSeries by viewModel.top10PersonalTvSeries.collectAsState()

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
            onRetry = {
                viewModel.viewModelScope.launch {
                    viewModel.initHomeTop10Personal()
                }
            }
        )
    }
}