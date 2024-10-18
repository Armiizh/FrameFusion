package com.example.framefusion.personInterest

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.framefusion.personInterest.ui.GreetingScreen
import com.example.framefusion.personInterest.ui.OnboardingScreen
import com.example.framefusion.utils.Constants

@Composable
fun GreetingNavHost(
    navController: NavHostController,
    onFinish: () -> Unit,
    modifier: Modifier,
    viewModel: PersonInterestViewModel
) {
    NavHost(
        navController = navController,
        startDestination = Constants.GreetingScreens.GREETING_SCREEN,
        modifier = modifier,
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