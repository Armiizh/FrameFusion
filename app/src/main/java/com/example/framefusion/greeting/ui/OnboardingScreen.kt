package com.example.framefusion.greeting.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewModelScope
import com.example.framefusion.R
import com.example.framefusion.greeting.PersonInterestViewModel
import com.example.framefusion.greeting.data.model.UserGenres
import com.example.framefusion.greeting.utils.composable.CheckItem
import com.example.framefusion.greeting.data.model.Genres
import com.example.framefusion.utils.ui.Background
import com.example.framefusion.utils.Constants
import kotlinx.coroutines.launch

@Composable
fun OnboardingScreen(
    onFinish: () -> Unit,
    viewModel: PersonInterestViewModel
) {
    val genres = Constants.GenresObject.greetingGenres
    val genreStates = genres.associateWith { genre ->
        val state = remember { mutableStateOf(genre.isSelected) }
        state
    }

    Scaffold(
        content = { paddingValues ->
            Background()
            Content(paddingValues, genres, genreStates)
        },
        bottomBar = {
            BottomBarContent(genreStates, viewModel, onFinish)
        }
    )
}

@Composable
private fun Content(
    paddingValues: PaddingValues,
    genres: List<Genres>,
    genreStates: Map<Genres, MutableState<Boolean>>
) {
    Column(
        modifier = Modifier
            .padding(paddingValues)
            .padding(horizontal = 8.dp)
            .fillMaxSize(),
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
            items(genres) { genre ->
                CheckItem(genre.name, genreStates[genre]!!, genre.imageResId)
            }
        }
    }
}

@Composable
private fun BottomBarContent(
    genreStates: Map<Genres, MutableState<Boolean>>,
    viewModel: PersonInterestViewModel,
    onFinish: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 4.dp, bottom = 16.dp)
            .padding(horizontal = 4.dp),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        ElevatedButton(
            modifier = Modifier.fillMaxWidth(),
            onClick = {
                val selectedGenres =
                    genreStates.filterValues { it.value }
                        .keys.joinToString(
                            separator = ","
                        ) { it.name }
                val userGenres = UserGenres(genres = selectedGenres)
                viewModel.viewModelScope.launch {
                    viewModel.insertGenres(userGenres)
                }
                onFinish()
            },
            elevation = ButtonDefaults.elevatedButtonElevation(8.dp),
            shape = RoundedCornerShape(12.dp),
            colors = ButtonDefaults.elevatedButtonColors(
                containerColor = MaterialTheme.colorScheme.primaryContainer,
                contentColor = MaterialTheme.colorScheme.onPrimaryContainer
            )
        ) {
            Text(
                text = "Начать"
            )
        }
    }
}