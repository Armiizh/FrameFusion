package com.example.framefusion.features.person.presentation

import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import com.example.framefusion.features.person.PersonScreenViewModel
import com.example.framefusion.features.person.utils.personFavoriteGenres.PersonGenresScreenContent
import com.example.framefusion.features.person.utils.personFavoriteGenres.PersonGenresTopAppBar
import com.example.framefusion.utils.Constants.AllGenresObject.allGenres
import com.example.framefusion.utils.navigation.Navigator

@Composable
fun PersonFavoriteGenresScreen(
    navigator: Navigator,
    personScreenViewModel: PersonScreenViewModel,
    initHomeDataAfterChangesGenres: () -> Unit
) {

    val allGenres = remember { allGenres }
    val allGenreStates = allGenres.associateWith { genre ->
        val state = remember { mutableStateOf(genre.isSelected) }
        state
    }
    val genres by personScreenViewModel.genres.collectAsState()

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
                navigator
            ) {
                personScreenViewModel.insertGenres(it)
                initHomeDataAfterChangesGenres()
            }
        },
        content = { paddingValues ->
            PersonGenresScreenContent(paddingValues, allGenres, allGenreStates)
        }
    )
}