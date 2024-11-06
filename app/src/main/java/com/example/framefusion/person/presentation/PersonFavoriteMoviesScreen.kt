package com.example.framefusion.person.presentation

import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.NavHostController
import com.example.framefusion.person.PersonScreenViewModel
import com.example.framefusion.person.utils.personFavoriteMovies.content.PersonFavoriteMoviesContent
import com.example.framefusion.utils.composable.SimpleTopAppBar

@Composable
fun PersonFavoriteMoviesScreen(
    viewModel: PersonScreenViewModel,
    navController: NavHostController,
    provideId: (Int?) -> Unit,
    onHomeScreen: () -> Unit,
    onSearchScreen: () -> Unit
) {
    val favoritesItem by viewModel.favorites.collectAsState()
    Scaffold(
        topBar = {
            SimpleTopAppBar(navController)
        },
        content = { paddingValues ->
            PersonFavoriteMoviesContent(
                paddingValues,
                favoritesItem,
                onHomeScreen,
                onSearchScreen,
                provideId
            )
        }
    )
}