package com.example.framefusion.home.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.framefusion.home.HomeScreenViewModel
import com.example.framefusion.home.utils.MovieItem
import com.example.framefusion.home.utils.TextList
import com.example.framefusion.home.utils.TvSeriesItem

@Composable
fun HomeScreen(
    homeScreenViewModel: HomeScreenViewModel
) {
    val movies by homeScreenViewModel.movies.collectAsState()
    val tvSeries by homeScreenViewModel.tvSeries.collectAsState()
    val isMovieLoading by homeScreenViewModel.isMovieLoading.collectAsState()
    val isTvSeriesLoading by homeScreenViewModel.isTvSeriesLoading.collectAsState()

    Scaffold(
        content = { paddingValues ->
            Column(
                modifier = Modifier
                    .padding(paddingValues)
                    .fillMaxWidth(),
            ) {
                TextList("Фильмы на основе ваших интересов:")
                Spacer(modifier = Modifier.height(12.dp))
                if (isMovieLoading) {
                    CircularProgressIndicator(
                        modifier = Modifier
                            .size(50.dp)
                            .align(Alignment.CenterHorizontally)
                    )
                } else {
                    LazyRow(
                        modifier = Modifier.padding(start = 8.dp)
                    ) {
                        items(movies) { movie ->
                            MovieItem(movie = movie)
                        }
                    }
                }
                Spacer(modifier = Modifier.height(12.dp))
                TextList("Сериалы на основе ваших интересов:")
                Spacer(modifier = Modifier.height(12.dp))
                if (isTvSeriesLoading) {
                    CircularProgressIndicator(
                        modifier = Modifier
                            .size(50.dp)
                            .align(Alignment.CenterHorizontally)
                    )
                } else {
                    LazyRow(
                        modifier = Modifier.padding(start = 8.dp)
                    ) {
                        items(tvSeries) { tvSeries ->
                            TvSeriesItem(tvSeries = tvSeries)
                        }
                    }
                }
            }
        }
    )
}