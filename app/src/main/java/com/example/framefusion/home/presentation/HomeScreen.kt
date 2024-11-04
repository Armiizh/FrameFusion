package com.example.framefusion.home.presentation

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.framefusion.R
import com.example.framefusion.home.HomeScreenViewModel
import com.example.framefusion.home.utils.composable.HomeTop10ItemsShimmer
import com.example.framefusion.home.utils.composable.MovieItem
import com.example.framefusion.home.utils.composable.TextList
import com.example.framefusion.home.utils.composable.TvSeriesItem
import com.example.framefusion.person.presentation.NameOfScreen
import com.example.framefusion.utils.ui.Background

@Composable
fun HomeScreen(
    homeScreenViewModel: HomeScreenViewModel,
    provideId: (Int?) -> Unit,
    onHomePersonalItems: (String) -> Unit
) {
    val isMovieLoading by homeScreenViewModel.top10PersonalMoviesLoading.collectAsState()
    val isTvSeriesLoading by homeScreenViewModel.top10PersonalTvSeriesLoading.collectAsState()

    Scaffold(
        topBar = {
            HomeScreenTopAppBar()
        },
        content = { paddingValues ->
            Background()
            Column(
                modifier = Modifier
                    .padding(paddingValues)
                    .padding(horizontal = 8.dp)
                    .padding(bottom = 80.dp)
                    .fillMaxWidth()
                    .verticalScroll(rememberScrollState()),
            ) {
                TextList("Фильмы на основе ваших интересов:")
                Spacer(modifier = Modifier.height(12.dp))
                if (isMovieLoading) {
                    HomeTop10ItemsShimmer()
                } else {
                    Row(
                        modifier = Modifier
                            .padding(horizontal = 8.dp)
                            .horizontalScroll(rememberScrollState()),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        val movies by homeScreenViewModel.top10PersonalMovies.collectAsState()
                        movies.forEach { movieItem ->
                            MovieItem(movieItem, provideId)
                        }
                        OnHomePersonalItemsScreenButton { onHomePersonalItems("movie") }
                    }
                }
                Spacer(modifier = Modifier.height(12.dp))
                TextList("Сериалы на основе ваших интересов:")
                Spacer(modifier = Modifier.height(12.dp))
                if (isTvSeriesLoading) {
                    HomeTop10ItemsShimmer()
                } else {
                    Row(
                        modifier = Modifier
                            .padding(horizontal = 8.dp)
                            .horizontalScroll(rememberScrollState()),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        val tvSeries by homeScreenViewModel.top10PersonalTvSeries.collectAsState()
                        tvSeries.forEach { tvSeriesItem ->
                            TvSeriesItem(tvSeriesItem, provideId)
                        }
                        OnHomePersonalItemsScreenButton { onHomePersonalItems("tv-series") }
                    }
                }
            }
        }
    )
}

@Composable
private fun OnHomePersonalItemsScreenButton(onHomePersonalItems: (String) -> Unit) {
    IconButton(
        onClick = { onHomePersonalItems("movie") }
    ) {
        Icon(
            modifier = Modifier.size(100.dp),
            imageVector = Icons.AutoMirrored.Rounded.KeyboardArrowRight,
            contentDescription = null
        )
    }
}

@Composable
@OptIn(ExperimentalMaterial3Api::class)
private fun HomeScreenTopAppBar() {
    TopAppBar(
        title = { NameOfScreen(stringResource(R.string.Home)) },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Color.Transparent
        )
    )
}