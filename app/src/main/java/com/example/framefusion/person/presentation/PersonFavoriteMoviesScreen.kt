package com.example.framefusion.person.presentation

import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import com.example.framefusion.person.PersonScreenViewModel
import com.example.framefusion.person.utils.personFavoriteMovies.content.PersonFavoriteMoviesContent
import com.example.framefusion.utils.composable.SimpleTopAppBar
import com.example.framefusion.utils.navigation.Navigator

@Composable
fun PersonFavoriteMoviesScreen(
    navigator: Navigator,
    personScreenViewModel: PersonScreenViewModel
) {
    Scaffold(
        topBar = {
            SimpleTopAppBar(navigator)
        },
        content = { paddingValues ->
            PersonFavoriteMoviesContent(paddingValues, navigator, personScreenViewModel)
        }
    )
}