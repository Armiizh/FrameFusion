package com.example.framefusion

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.framefusion.home.HomeScreenViewModel
import com.example.framefusion.home.ui.HomeScreen
import com.example.framefusion.itemDetails.DetailsScreenViewModel
import com.example.framefusion.itemDetails.ui.MovieItemDetailsScreen
import com.example.framefusion.itemDetails.ui.TvSeriesItemDetailsScreen
import com.example.framefusion.person.PersonScreenViewModel
import com.example.framefusion.person.ui.PersonFavoriteMoviesScreen
import com.example.framefusion.person.ui.PersonGenresScreen
import com.example.framefusion.person.ui.PersonScreen
import com.example.framefusion.person.ui.PersonSettingsScreen
import com.example.framefusion.search.SearchScreen
import com.example.framefusion.utils.Constants
import kotlinx.coroutines.launch

@Composable
fun NavHostContainer(
    navController: NavHostController,
    homeScreenViewModel: HomeScreenViewModel,
    personScreenViewModel: PersonScreenViewModel,
    detailsScreenViewModel: DetailsScreenViewModel
) {
    NavHost(
        navController = navController,
        startDestination = NavRoute.Home.route,
        builder = {
            composable(NavRoute.Home.route) {
                HomeScreen(
                    homeScreenViewModel,
                    provideMovieId = { movieId ->
                        Log.d("CheckId", "movie ID - $movieId")
                        val route = NavRoute.MovieDetails.createRoute(movieId!!.toString())
                        Log.d("CheckId", "route - $route")
                        navController.navigate(route)
                    },
                    provideTvSeriesId = { tvSeriesId ->
                        navController.navigate(NavRoute.TvSeriesDetails.createRoute(tvSeriesId!!))
                    })
            }

            composable(NavRoute.Search.route) {
                SearchScreen()
            }

            composable(NavRoute.Person.route) {
                PersonScreen(navController, personScreenViewModel)
            }

            composable(NavRoute.PersonGenres.route) {
                PersonGenresScreen(
                    personScreenViewModel,
                    navController,
                    updateGenres = {
                        homeScreenViewModel.viewModelScope.launch {
                            homeScreenViewModel.initData()
                        }
                    }
                )
            }

            composable(NavRoute.PersonFavorite.route) {
                PersonFavoriteMoviesScreen()
            }

            composable(NavRoute.PersonSettings.route) {
                PersonSettingsScreen()
            }
            composable(NavRoute.MovieDetails.route) {
                val movieId = navController.currentBackStackEntry?.arguments?.getString("movieId")
                MovieItemDetailsScreen(navController, detailsScreenViewModel, movieId?.toIntOrNull() ?: 0)
            }

            composable(NavRoute.TvSeriesDetails.route) { backStackEntry ->
                val tvSeriesId = backStackEntry.arguments?.getInt("tvSeriesId")
                TvSeriesItemDetailsScreen(navController, detailsScreenViewModel, tvSeriesId ?: 0)
            }
        }
    )
}

sealed class NavRoute(val route: String) {
    data object Home : NavRoute(Constants.Screens.HOME_SCREEN)
    data object Search : NavRoute(Constants.Screens.SEARCH_SCREEN)
    data object Person : NavRoute(Constants.Screens.PERSON_SCREEN)
    data object PersonGenres : NavRoute(Constants.Screens.PERSON_GENRES_SCREEN)
    data object PersonFavorite : NavRoute(Constants.Screens.PERSON_FAVORITE_MOVIES_SCREEN)
    data object PersonSettings : NavRoute(Constants.Screens.PERSON_SETTINGS_SCREEN)

    data object MovieDetails :
        NavRoute("${Constants.Screens.MOVIE_ITEM_DETAILS_SCREEN}/{movieId}") {
        fun createRoute(movieId: String): String {
            return "${Constants.Screens.MOVIE_ITEM_DETAILS_SCREEN}/$movieId"
        }
    }

    data object TvSeriesDetails :
        NavRoute("${Constants.Screens.TV_SERIES_ITEM_DETAILS_SCREEN}/{tvSeriesId}") {
        fun createRoute(tvSeriesId: Int) =
            "${Constants.Screens.TV_SERIES_ITEM_DETAILS_SCREEN}/$tvSeriesId"
    }
}