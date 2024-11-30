package com.example.framefusion.greeting

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.framefusion.greeting.presentation.GreetingScreen
import com.example.framefusion.greeting.presentation.OnboardingScreen
import com.example.framefusion.utils.Constants

@Composable
fun GreetingNavHost(
    navController: NavHostController,
    onFinish: () -> Unit
) {
    NavHost(
        navController = navController,
        startDestination = Constants.Screens.GreetingScreens.GREETING_SCREEN,
        builder = {

            composable(GreetingNavRoute.Greeting.route) {
                GreetingScreen { navController.navigate(GreetingNavRoute.Onboarding.route) }
            }

            composable(GreetingNavRoute.Onboarding.route) {
                OnboardingScreen(onFinish)
            }
        }
    )
}
sealed class GreetingNavRoute(val route: String) {
    data object Greeting : GreetingNavRoute(Constants.Screens.GreetingScreens.GREETING_SCREEN)
    data object Onboarding : GreetingNavRoute(Constants.Screens.GreetingScreens.ONBOARDING_SCREEN)
}