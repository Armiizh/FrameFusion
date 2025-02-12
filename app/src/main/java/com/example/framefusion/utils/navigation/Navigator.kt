package com.example.framefusion.utils.navigation

import androidx.navigation.NavHostController
import com.example.framefusion.utils.Constants

class Navigator(
    private val navController: NavHostController
) {

    fun navigateBack() {
        navController.popBackStack()
    }

    fun navigateToHome() {
        navController.navigate(NavRoute.Home.route)
    }

    fun navigateToHomeMore(type: String) {
        navController.navigate("${Constants.Screens.HomeScreensMore.HOME_SCREEN_MORE}/$type")
    }

    fun navigateToSearch() {
        navController.navigate(NavRoute.Search.route)
    }

    fun navigateToPerson() {
        navController.navigate(NavRoute.Person.route)
    }

    fun navigateToPersonFavoriteGenres() {
        navController.navigate(NavRoute.PersonFavoriteGenres.route)
    }

    fun navigateToPersonFavoriteMovies() {
        navController.navigate(NavRoute.PersonFavoriteMovies.route)
    }

    fun navigateToPersonFavoriteActors() {
        navController.navigate(NavRoute.PersonFavoriteActors.route)
    }

    fun navigateToPersonSettings() {
        navController.navigate(NavRoute.PersonSettings.route)
    }

    fun navigateToFullItemCast() {
        navController.navigate(NavRoute.FullItemCast.route)
    }

    fun navigateToActorDetails(id: Int?) {
        if (id != null) {

        }
        navController.navigate(NavRoute.ActorDetails.route)
    }

    fun navigateToItemDetails(id: Int?) {
        if (id != null) {
            navController.navigate("${Constants.Screens.ItemDetailsScreens.ITEM_DETAILS_SCREEN}/$id")
        }
    }
}

sealed class NavRoute(val route: String) {

    //Onboarding
    data object Greeting : NavRoute(Constants.Screens.GreetingScreens.GREETING_SCREEN)
    data object Onboarding : NavRoute(Constants.Screens.GreetingScreens.ONBOARDING_SCREEN)

    //Home
    data object Home : NavRoute(Constants.Screens.MainScreens.HOME_SCREEN)
    data object HomeMore : NavRoute("${Constants.Screens.HomeScreensMore.HOME_SCREEN_MORE}/{type}")

    //Search
    data object Search : NavRoute(Constants.Screens.MainScreens.SEARCH_SCREEN)

    //Person
    data object Person : NavRoute(Constants.Screens.MainScreens.PERSON_SCREEN)
    data object PersonFavoriteGenres :
        NavRoute(Constants.Screens.PersonScreens.PERSON_FAVORITE_GENRES_SCREEN)

    data object PersonFavoriteMovies :
        NavRoute(Constants.Screens.PersonScreens.PERSON_FAVORITE_MOVIES_SCREEN)

    data object PersonFavoriteActors :
        NavRoute(Constants.Screens.PersonScreens.PERSON_FAVORITE_ACTORS_SCREEN)

    data object PersonSettings : NavRoute(Constants.Screens.PersonScreens.PERSON_SETTINGS_SCREEN)

    //ItemDetails
    data object ItemDetails :
        NavRoute("${Constants.Screens.ItemDetailsScreens.ITEM_DETAILS_SCREEN}/{itemId}")

    data object FullItemCast : NavRoute(Constants.Screens.ItemDetailsScreens.FULL_ITEM_CAST_SCREEN)
    data object ActorDetails : NavRoute(Constants.Screens.ItemDetailsScreens.ACTOR_DETAILS_SCREEN)
}