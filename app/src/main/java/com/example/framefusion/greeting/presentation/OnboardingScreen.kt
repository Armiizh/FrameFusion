package com.example.framefusion.greeting.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.BottomAppBar
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewModelScope
import com.example.framefusion.R
import com.example.framefusion.greeting.GreetingScreenViewModel
import com.example.framefusion.greeting.data.model.Genres
import com.example.framefusion.greeting.utils.composable.CheckItem
import com.example.framefusion.utils.Constants
import com.example.framefusion.utils.ui.Background
import kotlinx.coroutines.launch

@Composable
fun OnboardingScreen(
    onFinish: () -> Unit
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
            BottomBarContent(genreStates, onFinish)
        }
    )
}

@Composable
private fun BottomBarContent(
    genreStates: Map<Genres, MutableState<Boolean>>,
    onFinish: () -> Unit,
    viewModel: GreetingScreenViewModel = hiltViewModel()
) {
    BottomAppBar(
        content = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 4.dp)
                    .padding(horizontal = 4.dp),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {

                val isEnabled = genreStates.values.any { it.value }
                val btnColor = if (isEnabled) {
                    MaterialTheme.colorScheme.secondary
                } else {
                    Color.LightGray
                }
                ElevatedButton(
                    modifier = Modifier.fillMaxWidth(),
                    onClick = {
                        viewModel.viewModelScope.launch {
                            viewModel.saveSelectedGenres(genreStates)
                        }
                        onFinish()
                    },
                    enabled = isEnabled,
                    elevation = ButtonDefaults.elevatedButtonElevation(8.dp),
                    shape = RoundedCornerShape(12.dp),
                    colors = ButtonDefaults.elevatedButtonColors(
                        containerColor = btnColor
                    )
                ) {
                    Text(
                        text = "Начать"
                    )
                }
            }
        },
        containerColor = Color.Transparent
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
            items(genres) { genre ->
                CheckItem(genre.name, genreStates[genre], genre.imageResId)
            }
        }
    }
}