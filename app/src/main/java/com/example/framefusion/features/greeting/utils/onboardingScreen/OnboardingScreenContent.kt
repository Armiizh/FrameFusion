package com.example.framefusion.features.greeting.utils.onboardingScreen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.framefusion.R
import com.example.framefusion.features.greeting.data.local.model.Genres
import com.example.framefusion.features.greeting.utils.composable.CheckItem

@Composable
fun OnboardingScreenContent(
    paddingValues: PaddingValues,
    genres: List<Genres>,
    genreStates: Map<Genres, MutableState<Boolean>>
) {

    val genreList by remember(genres) { mutableStateOf(genres) }

    Column(
        modifier = Modifier
            .padding(paddingValues)
            .padding(horizontal = 8.dp)
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(R.string.Choose_yout_favorite_genres),
            fontSize = 28.sp
        )

        LazyColumn(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.Start
        ) {
            items(
                items = genreList,
                key = { it.id }
            ) { genre ->
                CheckItem(genre.name, genreStates[genre], genre.imageResId)
            }
        }
    }
}