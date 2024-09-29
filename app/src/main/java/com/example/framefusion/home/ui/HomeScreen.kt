package com.example.framefusion.home.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.framefusion.home.HomeScreenViewModel
import com.example.framefusion.home.data.local.model.Movie

@Composable
fun HomeScreen(
    homeScreenViewModel: HomeScreenViewModel
) {
    val movies by homeScreenViewModel.movies.collectAsState()
    val isLoading by homeScreenViewModel.isLoading.collectAsState()

    Scaffold(
        content = { paddingValues ->
            Column(
                modifier = Modifier
                    .padding(paddingValues)
                    .fillMaxSize(),
            ) {
                Text(
                    modifier = Modifier.padding(start = 24.dp),
                    text = "Фильмы на основе ваших интересов",
                    fontSize = 30.sp,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onBackground
                )
                Spacer(modifier = Modifier.height(12.dp))
                if (isLoading) {
                    CircularProgressIndicator(
                        modifier = Modifier
                            .size(50.dp)
                            .align(Alignment.CenterHorizontally)
                    )
                } else {
                    LazyRow(
                        modifier = Modifier.padding(start = 24.dp)
                    ) {
                        items(movies) { movie ->
                            MovieItem(movie = movie)
                        }
                    }
                }
            }
        }
    )
}

@Composable
fun MovieItem(movie: Movie) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(end = 12.dp)
    ) {
        Column(
            modifier = Modifier
                .clip(RoundedCornerShape(12.dp)),
            verticalArrangement = Arrangement.Center
        ) {
            AsyncImage(
                model = ImageRequest
                    .Builder(LocalContext.current)
                    .data(movie.poster.url)
                    .size(coil.size.Size.ORIGINAL)
                    .build(),
                contentDescription = null
            )
        }
    }
}