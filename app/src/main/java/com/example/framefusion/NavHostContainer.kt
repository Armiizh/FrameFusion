package com.example.framefusion

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.framefusion.home.HomeScreenViewModel
import com.example.framefusion.home.ui.HomeScreen
import com.example.framefusion.person.PersonScreenViewModel
import com.example.framefusion.person.ui.PersonFavoriteMoviesScreen
import com.example.framefusion.person.ui.PersonGenresScreen
import com.example.framefusion.person.ui.PersonScreen
import com.example.framefusion.person.ui.PersonSettingsScreen
import com.example.framefusion.search.SearchScreen
import com.example.framefusion.utils.Constants

@Composable
fun NavHostContainer (
    navController: NavHostController,
    homeScreenViewModel: HomeScreenViewModel,
    personScreenViewModel: PersonScreenViewModel
) {
    NavHost(
        navController = navController,
        startDestination = NavRoute.Home.route,
        builder = {

            composable(NavRoute.Home.route) {
                HomeScreen(homeScreenViewModel)
            }

            composable(NavRoute.Search.route) {
                SearchScreen()
            }

            composable(NavRoute.Person.route) {
                PersonScreen(navController, personScreenViewModel)
            }

            composable(NavRoute.PersonGenres.route) {
                PersonGenresScreen(personScreenViewModel, navController)
            }

            composable(NavRoute.PersonFavorite.route) {
                PersonFavoriteMoviesScreen()
            }

            composable(NavRoute.PersonSettings.route) {
                PersonSettingsScreen()
            }
        }
    )
}
sealed class NavRoute(val route: String) {
    data object Home: NavRoute(Constants.Screens.HOME_SCREEN)
    data object Search: NavRoute(Constants.Screens.SEARCH_SCREEN)
    data object Person: NavRoute(Constants.Screens.PERSON_SCREEN)
    data object PersonGenres: NavRoute(Constants.Screens.PERSON_GENRES_SCREEN)
    data object PersonFavorite: NavRoute(Constants.Screens.PERSON_FAVORITE_MOVIES_SCREEN)
    data object PersonSettings: NavRoute(Constants.Screens.PERSON_SETTINGS_SCREEN)
}