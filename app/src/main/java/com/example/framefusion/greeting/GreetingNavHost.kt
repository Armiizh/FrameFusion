package com.example.framefusion.greeting

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.framefusion.greeting.ui.GreetingScreen
import com.example.framefusion.greeting.ui.OnboardingScreen
import com.example.framefusion.utils.Constants

@Composable
fun GreetingNavHost(
    navController: NavHostController,
    onFinish: () -> Unit,
    viewModel: PersonInterestViewModel
) {
    NavHost(
        navController = navController,
        startDestination = Constants.GreetingScreens.GREETING_SCREEN,
        builder = {

            composable(GreetingNavRoute.Greeting.route) {
                GreetingScreen { navController.navigate(GreetingNavRoute.Onboarding.route) }
            }

            composable(GreetingNavRoute.Onboarding.route) {
                OnboardingScreen(onFinish, viewModel)
            }
        }
    )
}
sealed class GreetingNavRoute(val route: String) {
    data object Greeting : GreetingNavRoute(Constants.GreetingScreens.GREETING_SCREEN)
    data object Onboarding : GreetingNavRoute(Constants.GreetingScreens.ONBOARDING_SCREEN)
}