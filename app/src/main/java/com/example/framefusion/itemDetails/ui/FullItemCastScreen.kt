package com.example.framefusion.itemDetails.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.framefusion.NavRoute
import com.example.framefusion.R
import com.example.framefusion.itemDetails.DetailsScreenViewModel
import com.example.framefusion.itemDetails.data.local.models.MovieDetails
import com.example.framefusion.itemDetails.utils.ErrorContent
import com.example.framefusion.utils.Background

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FullItemCastScreen(
    navController: NavHostController,
    detailsScreenViewModel: DetailsScreenViewModel
) {
    val movieDetails by detailsScreenViewModel.movieDetails.collectAsState()
    val isMovieLoading by detailsScreenViewModel.isMovieLoading.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = stringResource(R.string.Full_item_cast),
                        fontSize = 22.sp,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onBackground,
                        textAlign = TextAlign.Center
                    )
                },
                navigationIcon = {
                    Icon(
                        modifier = Modifier
                            .clickable { navController.navigate(NavRoute.Home.route) }
                            .padding(12.dp),
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.onBackground
                    )
                },
               colors = TopAppBarDefaults.topAppBarColors(
                   containerColor = Color.Transparent
               ),
                expandedHeight = 48.dp
            )
        },
        content = { paddingValues ->
            Background()
            Column(
                Modifier
                    .fillMaxWidth()
                    .padding(paddingValues)
                    .padding(horizontal = 8.dp)
                    .padding(bottom = 80.dp)
                    .verticalScroll(rememberScrollState()),
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
                        Content(movieDetails!!)
                    }
                }
            }

        }
    )
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun Content(
    movieDetails: MovieDetails
) {
    Column(Modifier.fillMaxWidth()) {
        FlowRow(
            Modifier.fillMaxWidth()
        ) {
            movieDetails.persons.forEach { person ->
                PersonItem(person)
            }
        }
    }
}