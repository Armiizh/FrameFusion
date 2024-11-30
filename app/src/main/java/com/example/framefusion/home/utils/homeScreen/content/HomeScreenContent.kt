package com.example.framefusion.home.utils.homeScreen.content

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewModelScope
import com.example.framefusion.home.HomeScreenViewModel
import com.example.framefusion.home.utils.homeScreen.HomeTop10PersonalContent
import com.example.framefusion.utils.Constants.MOVIES
import com.example.framefusion.utils.Constants.TV_SERIES
import com.example.framefusion.utils.Navigator
import com.example.framefusion.utils.composable.Poster
import com.example.framefusion.utils.ui.Background
import com.example.framefusion.utils.ui.FrameFusionColumn
import kotlinx.coroutines.launch

@Composable
fun HomeScreenContent(
    paddingValues: PaddingValues,
    navigator: Navigator,
    homeScreenViewModel: HomeScreenViewModel = hiltViewModel()
) {
    Background()

    LaunchedEffect(Unit) {
        homeScreenViewModel.viewModelScope.launch {
            homeScreenViewModel.initData()
        }
    }
    FrameFusionColumn(paddingValues) {

        //Фильмы
        val isMovieLoading by homeScreenViewModel.top10PersonalMoviesLoading.collectAsState()
        val movies by homeScreenViewModel.top10PersonalMovies.collectAsState()

        HomeTop10PersonalContent(
            type = MOVIES,
            isLoading = isMovieLoading,
            items = movies,
            onHomePersonalItemsScreen = {
                navigator.navigateToHomeMore("movie")
            },
            itemContent = { movieItem ->
                Poster(movieItem.poster.url) {
                    navigator.navigateToItemDetails(movieItem.id)
                }
            }
        )

        //Сериалы
        val isTvSeriesLoading by homeScreenViewModel.top10PersonalTvSeriesLoading.collectAsState()
        val tvSeries by homeScreenViewModel.top10PersonalTvSeries.collectAsState()

        HomeTop10PersonalContent(
            type = TV_SERIES,
            isLoading = isTvSeriesLoading,
            items = tvSeries,
            onHomePersonalItemsScreen = {
                navigator.navigateToHomeMore("tv-series")
            },
            itemContent = { tvSeriesItem ->
                Poster(tvSeriesItem.poster.url) {
                    navigator.navigateToItemDetails(tvSeriesItem.id)
                }
            }
        )
    }
}