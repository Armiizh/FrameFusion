package com.example.framefusion.itemDetails.ui

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.DividerDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import coil.size.Size
import com.example.framefusion.NavRoute
import com.example.framefusion.R
import com.example.framefusion.itemDetails.DetailsScreenViewModel
import com.example.framefusion.itemDetails.data.local.models.Backdrop
import com.example.framefusion.itemDetails.data.local.models.MovieDetails
import com.example.framefusion.itemDetails.utils.ErrorContent
import com.example.framefusion.itemDetails.utils.genreFormatted
import com.example.framefusion.itemDetails.utils.minutesToHoursAndMinutes
import com.example.framefusion.itemDetails.utils.ratingColor
import com.example.framefusion.utils.Background
import kotlinx.coroutines.launch
import java.util.Locale

@Composable
fun MovieItemDetailsScreen(
    navController: NavHostController,
    detailsScreenViewModel: DetailsScreenViewModel,
    id: Int
) {
    val movieDetails by detailsScreenViewModel.movieDetails.collectAsState()
    val isMovieLoading by detailsScreenViewModel.isMovieLoading.collectAsState()

    LaunchedEffect(Unit) {
        detailsScreenViewModel.viewModelScope.launch {
            detailsScreenViewModel.initMovie(id)
        }
    }

    Scaffold(
        content = { paddingValues ->
            Background()
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(paddingValues)
                    .padding(bottom = 80.dp)
            ) {
                if (isMovieLoading) {
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.Center
                    ) {
                        CircularProgressIndicator(
                            modifier = Modifier.fillMaxSize(),
                            color = colorResource(id = R.color.color1)
                        )
                    }
                } else {
                    if (movieDetails == null) {
                        ErrorContent()
                    } else {
                        Content(movieDetails!!, navController)
                    }
                }
            }
        }
    )
}


@Composable
private fun Content(
    movieDetails: MovieDetails,
    navController: NavHostController
) {
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        Box(
            modifier = Modifier.fillMaxWidth()
        ) {
            if (movieDetails.backdrop.url != null && movieDetails.backdrop.url != "null") {
                Backdrop(movieDetails)
            }
            Icon(
                modifier = Modifier
                    .clickable { navController.navigate(NavRoute.Home.route) }
                    .padding(12.dp),
                imageVector = Icons.Default.ArrowBack,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.onBackground
            )
        }
        val detailsGenres = genreFormatted(movieDetails)
        val ratingKp = movieDetails.rating.kp
        val time = minutesToHoursAndMinutes(movieDetails.movieLength?.toIntOrNull())
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 4.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(12.dp))
            MovieName(movieDetails)
            Spacer(modifier = Modifier.height(12.dp))
            MovieGenres(movieDetails, detailsGenres)
            Spacer(modifier = Modifier.height(4.dp))
            MovieParams(movieDetails, time, movieDetails.rating.kp!!, ratingKp)
            Spacer(modifier = Modifier.height(24.dp))
            Row(modifier = Modifier.fillMaxWidth()) {
                Text(
                    text = "Описание",
                    fontSize = 18.sp
                )
            }
            HorizontalDivider(
                thickness = DividerDefaults.Thickness,
                modifier = Modifier
                    .height(1.dp)
                    .fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(12.dp))
            Description(movieDetails)
        }
    }
}

@Composable
private fun Spacer() {
    Spacer(modifier = Modifier.height(12.dp))
    HorizontalDivider(
        thickness = DividerDefaults.Thickness,
        modifier = Modifier
            .height(1.dp)
            .fillMaxWidth()
    )
}

@Composable
private fun MovieName(movieDetails: MovieDetails) {
    Text(
        textAlign = TextAlign.Center,
        text = movieDetails.name.toString(),
        fontSize = 24.sp,
        fontWeight = FontWeight.Bold
    )
}

@Composable
private fun MovieGenres(
    movieDetails: MovieDetails,
    detailsGenres: String
) {
    if (movieDetails.genres.isNotEmpty()) {
        Text(
            textAlign = TextAlign.Center,
            text = " $detailsGenres"
        )
    }
}

@Composable
private fun MovieParams(
    movieDetails: MovieDetails,
    time: String,
    kp: Double,
    ratingKp: Double?
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center
    ) {
        if (movieDetails.year != null) {
            Text(text = "${movieDetails.year}")
        }
        if (time != "") {
            Text(text = " * $time")
        }
        if (movieDetails.rating.kp != null && kp.toFloat() != 0.0f) {
            Row {
                Text(text = " *")
                Text(
                    text = " $ratingKp",
                    color = ratingColor(ratingKp!!)
                )
            }
        }
    }
}

@Composable
private fun Description(movieDetails: MovieDetails) {
    val textDescription = if (movieDetails.description != null) {
        "${movieDetails.description}"
    } else if (movieDetails.shortDescription != null) {
        "${movieDetails.shortDescription}"
    } else {
        "Ребята пока не добавили описание к своему фильму.\n\nЗдесь могла бы быть ваша реклама :)"
    }

    var isExpanded by remember { mutableStateOf(false) }
    var actualLineCount by remember { mutableIntStateOf(0) }
    var maxLines by remember { mutableIntStateOf(6) }

    LazyColumn(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        item {
            Text(
                textAlign = TextAlign.Justify,
                modifier = Modifier.fillMaxWidth(),
                text = textDescription,
                maxLines = if (isExpanded) Int.MAX_VALUE else maxLines,
                onTextLayout = { textLayoutResult ->
                    actualLineCount = textLayoutResult.lineCount
                    if (actualLineCount > 5 && !isExpanded) {
                        maxLines = 6
                    }
                }
            )
            if (actualLineCount > 5) {
                TextButton(
                    onClick = {
                        isExpanded = !isExpanded
                    },
                ) {
                    Text(
                        text = if (isExpanded) {
                            "Свернуть"
                        } else {
                            "Развернуть"
                        },
                        modifier = Modifier.padding(8.dp),
                        color = MaterialTheme.colorScheme.onBackground
                    )
                }
            }
        }
    }
}

@Composable
private fun Backdrop(movieDetails: MovieDetails) {
    if (movieDetails.backdrop.url == null) {
        androidx.compose.foundation.layout.Spacer(modifier = Modifier.height(1.dp))
    } else {
        AsyncImage(
            modifier = Modifier
                .fillMaxWidth(),
            model = ImageRequest
                .Builder(LocalContext.current)
                .data(movieDetails.backdrop.url)
                .size(Size.ORIGINAL)
                .crossfade(true)
                .build(),
            contentDescription = null,
        )
    }
}