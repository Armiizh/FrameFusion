package com.example.framefusion.home.utils.homeScreen.content

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.example.framefusion.home.HomeScreenViewModel
import com.example.framefusion.home.utils.homeScreen.HomeTop10PersonalContent
import com.example.framefusion.utils.Constants.MOVIES
import com.example.framefusion.utils.Constants.TV_SERIES
import com.example.framefusion.utils.composable.Poster
import com.example.framefusion.utils.ui.Background
import com.example.framefusion.utils.ui.FrameFusionColumn

@Composable
fun HomeScreenContent(
    paddingValues: PaddingValues,
    viewModel: HomeScreenViewModel,
    onItemDetailsScreen: (Int?) -> Unit,
    onHomePersonalItemsScreen: (String) -> Unit
) {
    Background()

    FrameFusionColumn(paddingValues) {

        //Фильмы
        val isMovieLoading by viewModel.top10PersonalMoviesLoading.collectAsState()
        val movies by viewModel.top10PersonalMovies.collectAsState()

        HomeTop10PersonalContent(
            type = MOVIES,
            isLoading = isMovieLoading,
            items = movies,
            onHomePersonalItemsScreen = { onHomePersonalItemsScreen("movie") }
        ) { movieItem ->
            Poster(movieItem.poster.url) { onItemDetailsScreen(movieItem.id) }
        }

        //Сериалы
        val isTvSeriesLoading by viewModel.top10PersonalTvSeriesLoading.collectAsState()
        val tvSeries by viewModel.top10PersonalTvSeries.collectAsState()

        HomeTop10PersonalContent(
            type = TV_SERIES,
            isLoading = isTvSeriesLoading,
            items = tvSeries,
            onHomePersonalItemsScreen = { onHomePersonalItemsScreen("tv-series") }
        ) { tvSeriesItem ->
            Poster(tvSeriesItem.poster.url) { onItemDetailsScreen(tvSeriesItem.id) }
        }
    }
}