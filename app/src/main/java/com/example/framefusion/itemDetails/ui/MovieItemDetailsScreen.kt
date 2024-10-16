package com.example.framefusion.itemDetails.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.DividerDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavHostController
import com.example.framefusion.R
import com.example.framefusion.itemDetails.DetailsScreenViewModel
import com.example.framefusion.itemDetails.data.local.models.MovieDetails
import com.example.framefusion.itemDetails.utils.composable.Backdrop
import com.example.framefusion.itemDetails.utils.composable.Description
import com.example.framefusion.itemDetails.utils.composable.ErrorContent
import com.example.framefusion.itemDetails.utils.composable.IconBack
import com.example.framefusion.itemDetails.utils.composable.ItemGenres
import com.example.framefusion.itemDetails.utils.composable.ItemName
import com.example.framefusion.itemDetails.utils.composable.PersonItem
import com.example.framefusion.itemDetails.utils.composable.ProgressContent
import com.example.framefusion.itemDetails.utils.converters.genreFormatted
import com.example.framefusion.itemDetails.utils.converters.minutesToHoursAndMinutes
import com.example.framefusion.itemDetails.utils.converters.ratingColor
import com.example.framefusion.utils.ui.Background
import kotlinx.coroutines.launch

@Composable
fun MovieItemDetailsScreen(
    navController: NavHostController,
    detailsScreenViewModel: DetailsScreenViewModel,
    id: Int,
    onFullCastScreen:() -> Unit
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
                    ProgressContent()
                } else {
                    if (movieDetails == null) {
                        ErrorContent()
                    } else {
                        MovieContent(movieDetails!!, navController, onFullCastScreen)
                    }
                }
            }
        }
    )
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun MovieContent(
    movieDetails: MovieDetails,
    navController: NavHostController,
    onFullCastScreen: () -> Unit
) {
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        Box(
            modifier = Modifier.fillMaxWidth()
        ) {
            val url = if (movieDetails.backdrop.url != null && movieDetails.backdrop.url != "null") {
                movieDetails.backdrop.url.toString()
            } else {
                null
            }
            Backdrop(url)
            IconBack(navController)
        }
        val detailsGenres = genreFormatted(movieDetails.genres)
        val ratingKp = movieDetails.rating.kp
        val time = minutesToHoursAndMinutes(movieDetails.movieLength?.toIntOrNull())
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(12.dp))
            ItemName(movieDetails.name.toString())
            Spacer(modifier = Modifier.height(12.dp))
            if (movieDetails.genres.isNotEmpty()) {
                ItemGenres(detailsGenres)
            }
            Spacer(modifier = Modifier.height(2.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                val yearText = movieDetails.year ?: ""
                val totalLengthText = if (time.isNotEmpty()) " * $time" else ""
                Text(text = "$yearText$totalLengthText")
                if (movieDetails.rating.kp != null && ratingKp?.toFloat() != 0.0f) {
                    Row {
                        Text(text = " *")
                        Text(
                            text = " $ratingKp",
                            color = ratingColor(ratingKp!!)
                        )
                    }
                }
            }
            Spacer(modifier = Modifier.height(18.dp))
        }
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(modifier = Modifier.fillMaxWidth()) {
                Text(text = stringResource(R.string.Description), fontSize = 18.sp)
            }
            HorizontalDivider(
                thickness = DividerDefaults.Thickness,
                modifier = Modifier
                    .height(1.dp)
                    .fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(12.dp))
            val textDescription = if (movieDetails.description != null) {
                "${movieDetails.description}"
            } else if (movieDetails.shortDescription != null) {
                "${movieDetails.shortDescription}"
            } else {
                stringResource(R.string.Null_Description)
            }
            Description(textDescription)
            Spacer(modifier = Modifier.height(12.dp))
            Row(Modifier.fillMaxWidth()) { Text(text = stringResource(R.string.Cast)) }
            HorizontalDivider(
                thickness = DividerDefaults.Thickness,
                modifier = Modifier
                    .height(1.dp)
                    .fillMaxWidth()
            )
            FlowRow(
                modifier = Modifier.fillMaxWidth(),
                maxLines = 2
            ) {
                movieDetails.persons.take(4).forEach { person ->
                    PersonItem(person)
                }
            }
            Spacer(modifier = Modifier.height(12.dp))
            Row(
                Modifier.fillMaxWidth()
            ) {
                Text(
                    text = stringResource(R.string.Full_Cast),
                    color = MaterialTheme.colorScheme.onBackground,
                    textDecoration = TextDecoration.Underline,
                    modifier = Modifier.clickable { onFullCastScreen() }
                )
            }
        }
    }
}