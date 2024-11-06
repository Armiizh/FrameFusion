package com.example.framefusion.person.presentation

import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import com.example.framefusion.person.PersonScreenViewModel
import com.example.framefusion.person.utils.personFavoriteGenres.PersonGenresScreenContent
import com.example.framefusion.person.utils.personFavoriteGenres.PersonGenresTopAppBar
import com.example.framefusion.utils.Constants

@Composable
fun PersonFavoriteGenresScreen(
    viewModel: PersonScreenViewModel,
    navController: NavHostController,
    updateGenres: () -> Unit
) {

    val allGenres = Constants.AllGenresObject.allGenres
    val allGenreStates = allGenres.associateWith { genre ->
        val state = remember { mutableStateOf(genre.isSelected) }
        state
    }
    val genres by viewModel.genres.collectAsState(initial = emptyList())
    LaunchedEffect(Unit) {
        val selectedGenres = genres.map { it.trim() }
        allGenres.forEach { genre ->
            allGenreStates[genre]?.value = selectedGenres.contains(genre.name)
        }
    }
    Scaffold(
        topBar = {
            PersonGenresTopAppBar(
                allGenres,
                allGenreStates,
                viewModel,
                updateGenres,
                navController
            )
        },
        content = { paddingValues ->
            PersonGenresScreenContent(paddingValues, allGenres, allGenreStates)
        }
    )
}