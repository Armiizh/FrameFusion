package com.example.framefusion

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.framefusion.home.HomeScreen
import com.example.framefusion.person.PersonScreen
import com.example.framefusion.search.SearchScreen
import com.example.framefusion.utils.Constants

@Composable
fun NavHostContainer(
    navController: NavHostController,
    padding: PaddingValues
) {

    NavHost(
        navController = navController,
        startDestination = NavRoute.Home.route,
        modifier = Modifier.padding(paddingValues = padding),
        builder = {

            composable(NavRoute.Home.route) {
                HomeScreen()
            }

            composable(NavRoute.Search.route) {
                SearchScreen()
            }

            composable(NavRoute.Person.route) {
                PersonScreen()
            }
        }
    )
}
sealed class NavRoute(val route: String) {
    data object Home: NavRoute(Constants.Screens.HOME_SCREEN)
    data object Search: NavRoute(Constants.Screens.SEARCH_SCREEN)
    data object Person: NavRoute(Constants.Screens.PERSON_SCREEN)
}