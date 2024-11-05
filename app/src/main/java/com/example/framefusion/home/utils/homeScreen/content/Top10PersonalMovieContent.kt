package com.example.framefusion.home.utils.homeScreen.content

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.framefusion.home.HomeScreenViewModel
import com.example.framefusion.home.utils.homeScreen.HomeTop10ItemsShimmer
import com.example.framefusion.home.utils.homeScreen.MovieItem
import com.example.framefusion.home.utils.homeScreen.OnHomePersonalItemsScreenButton
import com.example.framefusion.utils.composable.TextList

@Composable
fun Top10PersonalMovieContent(
    homeScreenViewModel: HomeScreenViewModel,
    provideId: (Int?) -> Unit,
    onHomePersonalItems: (String) -> Unit
) {
    val isMovieLoading by homeScreenViewModel.top10PersonalMoviesLoading.collectAsState()
    TextList("Фильмы на основе ваших интересов:")
    Spacer(modifier = Modifier.height(8.dp))
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
}

