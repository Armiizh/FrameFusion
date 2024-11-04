package com.example.framefusion

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.framefusion.home.HomeScreenViewModel
import com.example.framefusion.home.presentation.HomePersonalItemsScreen
import com.example.framefusion.home.presentation.HomeScreen
import com.example.framefusion.itemDetails.DetailsScreenViewModel
import com.example.framefusion.itemDetails.presentation.FullItemCastScreen
import com.example.framefusion.itemDetails.presentation.ItemDetailsScreen
import com.example.framefusion.person.PersonScreenViewModel
import com.example.framefusion.person.presentation.PersonFavoriteMoviesScreen
import com.example.framefusion.person.presentation.PersonGenresScreen
import com.example.framefusion.person.presentation.PersonScreen
import com.example.framefusion.person.presentation.PersonSettingsScreen
import com.example.framefusion.search.SearchItemViewModel
import com.example.framefusion.search.presentation.SearchScreen
import com.example.framefusion.utils.Constants
import kotlinx.coroutines.launch

@Composable
fun NavHostContainer(
    navController: NavHostController,
    homeScreenViewModel: HomeScreenViewModel,
    personScreenViewModel: PersonScreenViewModel,
    detailsScreenViewModel: DetailsScreenViewModel,
    searchItemViewModel: SearchItemViewModel
) {
    NavHost(
        navController = navController,
        startDestination = NavRoute.Home.route,
        builder = {
            //Home
            composable(NavRoute.Home.route) {
                HomeScreen(
                    homeScreenViewModel,
                    provideId = { id ->
                        if (id != null) {
                            detailsScreenViewModel.viewModelScope.launch {
                                detailsScreenViewModel.initItemDetails(id)
                            }
                        }
                        navController.navigate(NavRoute.ItemDetails.route)
                    },
                    onHomePersonalItems = { type ->
                        homeScreenViewModel.viewModelScope.launch {
                            homeScreenViewModel.getHomePersonalItems(type)
                            homeScreenViewModel.initHomePersonalItems()
                        }
                        navController.navigate(NavRoute.HomeMore.route)
                    }
                )
            }
            composable(NavRoute.HomeMore.route) {
                HomePersonalItemsScreen(
                    homeScreenViewModel,
                    navController,
                    provideId = { id ->
                        if (id != null) {
                            detailsScreenViewModel.viewModelScope.launch {
                                detailsScreenViewModel.initItemDetails(id)
                            }
                        }
                        navController.navigate(NavRoute.ItemDetails.route)
                    }
                )
            }

            //Search
            composable(NavRoute.Search.route) {
                SearchScreen(
                    searchItemViewModel,
                    provideId = { id ->
                        if (id != null) {
                            detailsScreenViewModel.viewModelScope.launch {
                                detailsScreenViewModel.initItemDetails(id)
                            }
                        }
                        navController.navigate(NavRoute.ItemDetails.route)
                    }
                )
            }

            //Person
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
                PersonFavoriteMoviesScreen(
                    personScreenViewModel,
                    navController,
                    provideId = { id ->
                        if (id != null) {
                            detailsScreenViewModel.viewModelScope.launch {
                                detailsScreenViewModel.initItemDetails(id)
                            }
                        }
                        navController.navigate(NavRoute.ItemDetails.route)
                    },
                    onHomeScreen = { navController.navigate(NavRoute.Home.route) },
                    onSearchScreen = { navController.navigate(NavRoute.Search.route) }
                )
            }
            composable(NavRoute.PersonSettings.route) {
                PersonSettingsScreen(navController)
            }

            //ItemDetails
            composable(NavRoute.FullItemCast.route) {
                FullItemCastScreen(navController, detailsScreenViewModel)
            }
            composable(NavRoute.ItemDetails.route) {
                ItemDetailsScreen(
                    navController,
                    detailsScreenViewModel,
                    onFullCastScreen = { navController.navigate(NavRoute.FullItemCast.route) },
                    changeStatus = { item, isLiked ->
                        personScreenViewModel.viewModelScope.launch {
                            personScreenViewModel.changeFavoriteStatus(item, isLiked)
                            personScreenViewModel.initData()
                        }
                        detailsScreenViewModel.viewModelScope.launch {
                            detailsScreenViewModel.updateItem(item, isLiked)
                        }
                    }
                )
            }
        }
    )
}

sealed class NavRoute(val route: String) {

    //Home
    data object Home : NavRoute(Constants.Screens.HOME_SCREEN)
    data object HomeMore : NavRoute(Constants.Screens.HOME_SCREEN_MORE)

    //Search
    data object Search : NavRoute(Constants.Screens.SEARCH_SCREEN)

    //Person
    data object Person : NavRoute(Constants.Screens.PERSON_SCREEN)
    data object PersonGenres : NavRoute(Constants.Screens.PERSON_GENRES_SCREEN)
    data object PersonFavorite : NavRoute(Constants.Screens.PERSON_FAVORITE_MOVIES_SCREEN)
    data object PersonSettings : NavRoute(Constants.Screens.PERSON_SETTINGS_SCREEN)

    //ItemDetails
    data object FullItemCast : NavRoute(Constants.Screens.FULL_ITEM_CAST_SCREEN)
    data object ItemDetails : NavRoute(Constants.Screens.ITEM_DETAILS_SCREEN)
}