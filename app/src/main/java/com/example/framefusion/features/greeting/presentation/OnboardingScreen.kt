package com.example.framefusion.features.greeting.presentation

import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import com.example.framefusion.features.greeting.utils.onboardingScreen.OnboardingScreenBottomBarContent
import com.example.framefusion.features.greeting.utils.onboardingScreen.OnboardingScreenContent
import com.example.framefusion.utils.Constants.GenresObject.greetingGenres
import com.example.framefusion.utils.ui.Background

@Composable
fun OnboardingScreen(
    onFinish: () -> Unit
) {
    val genres = remember { greetingGenres }
    val genreStates = remember(genres) {
        genres.associateWith { genre -> mutableStateOf(genre.isSelected) }
    }

    Scaffold(
        content = { paddingValues ->
            Background()
            OnboardingScreenContent(paddingValues, genres, genreStates)
        },
        bottomBar = {
            OnboardingScreenBottomBarContent(genreStates, onFinish)
        }
    )
}