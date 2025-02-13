package com.example.framefusion.features.greeting.presentation

import androidx.compose.runtime.Composable
import com.example.framefusion.features.greeting.utils.greetingScreen.GreetingScreenContent
import com.example.framefusion.utils.navigation.Navigator
import com.example.framefusion.utils.ui.Background

@Composable
fun GreetingScreen(
    navigator: Navigator
) {
    Background()
    GreetingScreenContent(navigator)
}