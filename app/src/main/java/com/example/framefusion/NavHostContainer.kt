package com.example.framefusion

import android.app.Activity.MODE_PRIVATE
import android.content.SharedPreferences
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.framefusion.features.greeting.presentation.GreetingScreen
import com.example.framefusion.features.greeting.presentation.OnboardingScreen
import com.example.framefusion.features.home.HomeScreenViewModel
import com.example.framefusion.features.home.presentation.HomePersonalItemsScreen
import com.example.framefusion.features.home.presentation.HomeScreen
import com.example.framefusion.features.itemDetails.DetailsScreenViewModel
import com.example.framefusion.features.itemDetails.presentation.ActorsDetailsScreen
import com.example.framefusion.features.itemDetails.presentation.FullItemCastScreen
import com.example.framefusion.features.itemDetails.presentation.ItemDetailsScreen
import com.example.framefusion.features.person.PersonScreenViewModel
import com.example.framefusion.features.person.presentation.PersonFavoriteActorsScreen
import com.example.framefusion.features.person.presentation.PersonFavoriteGenresScreen
import com.example.framefusion.features.person.presentation.PersonFavoriteMoviesScreen
import com.example.framefusion.features.person.presentation.PersonScreen
import com.example.framefusion.features.person.presentation.PersonSettingsScreen
import com.example.framefusion.features.search.presentation.SearchScreen
import com.example.framefusion.utils.composable.BottomNavigationBar
import com.example.framefusion.utils.navigation.NavRoute
import com.example.framefusion.utils.navigation.Navigator

@Composable
fun NavHostContainer(
    navController: NavHostController = rememberNavController(),
    homeScreenViewModel: HomeScreenViewModel = hiltViewModel(),
    personScreenViewModel: PersonScreenViewModel = hiltViewModel(),
    detailsScreenViewModel: DetailsScreenViewModel = hiltViewModel()
) {
    val navigator = Navigator(navController)

    val context = LocalContext.current
    val prefs: SharedPreferences = context.getSharedPreferences("user_prefs", MODE_PRIVATE)
    val isFirstLaunch = remember { mutableStateOf(prefs.getBoolean("first_launch", true)) }

    if (isFirstLaunch.value) {
        Scaffold { paddingValues ->
            val pad = paddingValues
            NavHost(
                navController = navController,
                startDestination = NavRoute.Greeting.route,
                builder = {
                    //Greeting
                    composable(NavRoute.Greeting.route) {
                        GreetingScreen(navigator)
                    }
                    composable(NavRoute.Onboarding.route) {
                        OnboardingScreen {
                            prefs.edit().putBoolean("first_launch", false).apply()
                            isFirstLaunch.value = false
                        }
                    }
                }
            )
        }
    }

    if (!isFirstLaunch.value) {

        Scaffold(
            bottomBar = { BottomNavigationBar(navController) }
        ) { paddingValues ->
            val pad = paddingValues
            NavHost(
                navController = navController,
                startDestination = NavRoute.Home.route,
                builder = {
                    //Home
                    composable(NavRoute.Home.route) {
                        HomeScreen(navigator, homeScreenViewModel)
                    }
                    composable(
                        NavRoute.HomeMore.route,
                        listOf(navArgument("type") { type = NavType.StringType })
                    ) { backStackEntry ->
                        val type = backStackEntry.arguments?.getString("type")
                        HomePersonalItemsScreen(navigator, type, homeScreenViewModel)
                    }

                    //Search
                    composable(NavRoute.Search.route) {
                        SearchScreen(navigator)
                    }

                    //Person
                    composable(NavRoute.Person.route) {
                        PersonScreen(navigator, personScreenViewModel)
                    }
                    composable(NavRoute.PersonFavoriteGenres.route) {
                        PersonFavoriteGenresScreen(navigator, personScreenViewModel) {
                            homeScreenViewModel.onRetry()
                        }
                    }
                    composable(NavRoute.PersonFavoriteMovies.route) {
                        PersonFavoriteMoviesScreen(navigator, personScreenViewModel)
                    }
                    composable(NavRoute.PersonFavoriteActors.route) {
                        PersonFavoriteActorsScreen(navigator)
                    }
                    composable(NavRoute.PersonSettings.route) {
                        PersonSettingsScreen(navigator)
                    }

                    //ItemDetails
                    composable(
                        route = NavRoute.ItemDetails.route,
                        arguments = listOf(navArgument("itemId") { type = NavType.IntType })
                    ) { backStackEntry ->
                        val itemId = backStackEntry.arguments?.getInt("itemId")
                        ItemDetailsScreen(
                            navigator,
                            itemId ?: -1,
                            personScreenViewModel,
                            detailsScreenViewModel
                        )
                    }
                    composable(NavRoute.FullItemCast.route) {
                        FullItemCastScreen(navigator)
                    }
                    composable(
                        route = NavRoute.ActorDetails.route,
                        arguments = listOf(navArgument("id") { type = NavType.IntType })
                    ) { backStackEntry ->
                        val id = backStackEntry.arguments?.getInt("id")
                        ActorsDetailsScreen(navigator, id ?: -1, detailsScreenViewModel)
                    }
                }
            )
        }
    }
}