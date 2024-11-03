package com.example.framefusion.home.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.KeyboardArrowRight
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.framefusion.home.HomeScreenViewModel
import com.example.framefusion.home.utils.composable.HomeTop10ItemsShimmer
import com.example.framefusion.home.utils.composable.MovieItem
import com.example.framefusion.home.utils.composable.TextList
import com.example.framefusion.home.utils.composable.TvSeriesItem
import com.example.framefusion.person.presentation.NameOfScreen
import com.example.framefusion.utils.ui.Background

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    homeScreenViewModel: HomeScreenViewModel,
    provideId: (Int?) -> Unit,
    onHomePersonalItems: (Int, String) -> Unit
) {
    val movies by homeScreenViewModel.top10PersonalMovies.collectAsState()
    val tvSeries by homeScreenViewModel.top10PersonalTvSeries.collectAsState()
    val isMovieLoading by homeScreenViewModel.top10PersonalMoviesLoading.collectAsState()
    val isTvSeriesLoading by homeScreenViewModel.top10PersonalTvSeriesLoading.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { NameOfScreen("Главная") },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.Transparent
                )
            )
        },
        content = { paddingValues ->
            Background()
            Column(
                modifier = Modifier
                    .padding(paddingValues)
                    .fillMaxWidth()
                    .verticalScroll(rememberScrollState()),
            ) {
                TextList("Фильмы на основе ваших интересов:")
                Spacer(modifier = Modifier.height(12.dp))
                if (isMovieLoading) {
                    HomeTop10ItemsShimmer()
                } else {
                    LazyRow(
                        modifier = Modifier.padding(start = 8.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        items(movies) { movie ->
                            MovieItem(movie, provideId)
                        }
                        item {
                            IconButton(
                                onClick = { onHomePersonalItems(1, "movie") }
                            ) {
                                Icon(
                                    modifier = Modifier.size(100.dp),
                                    imageVector = Icons.AutoMirrored.Rounded.KeyboardArrowRight,
                                    contentDescription = null
                                )
                            }
                        }
                    }
                }
                Spacer(modifier = Modifier.height(12.dp))
                TextList("Сериалы на основе ваших интересов:")
                Spacer(modifier = Modifier.height(12.dp))
                if (isTvSeriesLoading) {
                    HomeTop10ItemsShimmer()
                } else {
                    LazyRow(
                        modifier = Modifier.padding(start = 8.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        items(tvSeries) { tvSeries ->
                            TvSeriesItem(tvSeries, provideId)
                        }
                        item {
                            IconButton(
                                onClick = { onHomePersonalItems(1, "tv-series") }
                            ) {
                                Icon(
                                    modifier = Modifier.size(100.dp),
                                    imageVector = Icons.AutoMirrored.Rounded.KeyboardArrowRight,
                                    contentDescription = null
                                )
                            }
                        }
                    }
                }
            }
        }
    )
}