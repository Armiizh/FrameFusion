package com.example.framefusion.person.presentation

import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import com.example.framefusion.person.utils.personFavoriteMovies.content.PersonFavoriteMoviesContent
import com.example.framefusion.utils.Navigator
import com.example.framefusion.utils.composable.SimpleTopAppBar

@Composable
fun PersonFavoriteMoviesScreen(
    navigator: Navigator
) {
    Scaffold(
        topBar = {
            SimpleTopAppBar(navigator)
        },
        content = { paddingValues ->
            PersonFavoriteMoviesContent(paddingValues, navigator)
        }
    )
}