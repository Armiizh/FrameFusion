package com.example.framefusion.features.person.presentation

import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import com.example.framefusion.features.person.PersonScreenViewModel
import com.example.framefusion.features.person.utils.personFavoriteActors.PersonFavoriteActorsContent
import com.example.framefusion.utils.composable.SimpleTopAppBar
import com.example.framefusion.utils.navigation.Navigator

@Composable
fun PersonFavoriteActorsScreen(
    navigator: Navigator,
    personScreenViewModel: PersonScreenViewModel
) {
    Scaffold(
        topBar = {
            SimpleTopAppBar(navigator)
        },
        content = { paddingValues ->
            PersonFavoriteActorsContent(paddingValues, navigator, personScreenViewModel)
        }
    )
}