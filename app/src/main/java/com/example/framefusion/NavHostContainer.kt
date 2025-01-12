package com.example.framefusion

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.framefusion.home.HomeScreenViewModel
import com.example.framefusion.home.presentation.HomePersonalItemsScreen
import com.example.framefusion.home.presentation.HomeScreen
import com.example.framefusion.itemDetails.presentation.ActorsDetailsScreen
import com.example.framefusion.itemDetails.presentation.FullItemCastScreen
import com.example.framefusion.itemDetails.presentation.ItemDetailsScreen
import com.example.framefusion.person.presentation.PersonFavoriteActorsScreen
import com.example.framefusion.person.presentation.PersonFavoriteGenresScreen
import com.example.framefusion.person.presentation.PersonFavoriteMoviesScreen
import com.example.framefusion.person.presentation.PersonScreen
import com.example.framefusion.person.presentation.PersonSettingsScreen
import com.example.framefusion.search.presentation.SearchScreen
import com.example.framefusion.utils.navigation.NavRoute
import com.example.framefusion.utils.navigation.Navigator

@Composable
fun NavHostContainer(
    navController: NavHostController,
    homeScreenViewModel: HomeScreenViewModel = hiltViewModel()
) {
    val navigator = Navigator(navController, homeScreenViewModel)

    NavHost(
        navController = navController,
        startDestination = NavRoute.Home.route,
        builder = {
            //Home
            composable(NavRoute.Home.route) {
                HomeScreen(navigator)
            }
            composable(NavRoute.HomeMore.route) {
                HomePersonalItemsScreen(navigator)
            }

            //Search
            composable(NavRoute.Search.route) {
                SearchScreen(navigator)
            }

            //Person
            composable(NavRoute.Person.route) {
                PersonScreen(navigator)
            }
            composable(NavRoute.PersonFavoriteGenres.route) {
                PersonFavoriteGenresScreen(navigator)
            }
            composable(NavRoute.PersonFavoriteMovies.route) {
                PersonFavoriteMoviesScreen(navigator)
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
                ItemDetailsScreen(navigator, itemId ?: -1)
            }
            composable(NavRoute.FullItemCast.route) {
                FullItemCastScreen(navigator)
            }
            composable(NavRoute.ActorDetails.route) {
                ActorsDetailsScreen(navigator)
            }
        }
    )
}