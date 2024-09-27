package com.example.framefusion

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.framefusion.home.HomeScreenViewModel
import com.example.framefusion.home.ui.HomeScreen
import com.example.framefusion.person.PersonScreen
import com.example.framefusion.personInterest.PersonInterestViewModel
import com.example.framefusion.search.SearchScreen
import com.example.framefusion.utils.Constants
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@Composable
fun NavHostContainer (
    navController: NavHostController,
    padding: PaddingValues,
    homeScreenViewModel: HomeScreenViewModel
) {
    NavHost(
        navController = navController,
        startDestination = NavRoute.Home.route,
        modifier = Modifier.padding(paddingValues = padding),
        builder = {

            composable(NavRoute.Home.route) {
                HomeScreen(homeScreenViewModel)
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