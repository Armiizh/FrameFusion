package com.example.framefusion.itemDetails.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.DividerDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
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
import com.example.framefusion.itemDetails.data.local.models.MovieDetails
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
            Image(
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop,
                painter = painterResource(id = R.drawable.background1),
                contentDescription = null
            )
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(paddingValues)
            ) {
                if (isMovieLoading) {
                    CircularProgressIndicator(
                        modifier = Modifier
                            .size(240.dp),
                        color = colorResource(id = R.color.color1)
                    )
                } else {
                    Box(
                        modifier = Modifier.fillMaxWidth()
                    ) {

                        Backdrop(movieDetails!!)

                        Icon(
                            modifier = Modifier
                                .clickable { navController.navigate(NavRoute.Home.route) }
                                .padding(12.dp),
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.onBackground
                        )
                        Content(movieDetails!!)
                    }
                }
            }
        }
    )
}


@Composable
private fun Content(
    movieDetails: MovieDetails
) {
    val detailsGenres = genreFormatted(movieDetails)
    val ratingKp = movieDetails.rating.kp
    val time = minutesToHoursAndMinutes(movieDetails.movieLength?.toInt()!!)
    val fraction = if (movieDetails.backdrop.url == null) 0.1f else 0.3f
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 4.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.fillMaxHeight(fraction))
        MovieName(movieDetails)
        Spacer(modifier = Modifier.height(12.dp))
        MovieGenres(movieDetails, detailsGenres)
        Spacer(modifier = Modifier.height(4.dp))
        MovieParams(movieDetails, time, movieDetails.rating.kp!!, ratingKp)
        Spacer(modifier = Modifier.height(24.dp))
        Row(modifier = Modifier.fillMaxWidth()) {
            Text(
                text = "Описюние",
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
        Spacer()
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
        if (movieDetails.movieLength != null) {
            Text(text = " * $time")
        }
        if (movieDetails.rating.kp != null && kp.toFloat() != 0.0f) {
            Row {
                Text(text = " *")
                Text(
                    text = " $ratingKp",
                    color = textColor(ratingKp!!)
                )
            }
        }
    }
}

@Composable
private fun Description(movieDetails: MovieDetails) {

    if (movieDetails.description == null) {
        Text(
            textAlign = TextAlign.Justify,
            modifier = Modifier.fillMaxWidth(),
            text = "${movieDetails.shortDescription}."
        )
    } else {
        Text(
            textAlign = TextAlign.Justify,
            modifier = Modifier.fillMaxWidth(),
            text = "${movieDetails.description}"
        )
    }
}

fun textColor(ratingKp: Double): Color {
    val color: Color = if (ratingKp > 7.0f) {
        Color.Green
    } else if (ratingKp < 7.0f && ratingKp > 4.0f) {
        Color.Yellow
    } else {
        Color.Red
    }
    return color
}


@Composable
private fun Backdrop(movieDetails: MovieDetails) {
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

private fun minutesToHoursAndMinutes(minutes: Int): String {
    val hours = minutes / 60
    val remainingMinutes = minutes % 60
    return "${hours}ч ${remainingMinutes}мин"
}

private fun genreFormatted(movieDetails: MovieDetails?) =
    movieDetails?.genres?.joinToString(separator = " -") {
        it.name!!.split(" ").joinToString(" ") { word -> word.capitalize(Locale.ROOT) }
    } ?: ""