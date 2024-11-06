package com.example.framefusion.person.utils.personScreen

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import com.example.framefusion.NavRoute
import com.example.framefusion.R
import com.example.framefusion.person.PersonScreenViewModel
import com.example.framefusion.utils.ui.Background
import com.example.framefusion.utils.ui.FrameFusionColumn

@Composable
fun PersonScreenContent(
    paddingValues: PaddingValues,
    personScreenViewModel: PersonScreenViewModel,
    navController: NavHostController
) {
    Background()
    FrameFusionColumn(paddingValues) {

        MenuItem(stringResource(R.string.PersonMenuItem1)) {
            personScreenViewModel.getPersonGenres()
            navController.navigate(NavRoute.PersonFavoriteGenres.route)
        }
        MenuItem(stringResource(R.string.PersonMenuItem2)) {
            personScreenViewModel.getPersonGenres()
            navController.navigate(NavRoute.PersonFavoriteMovies.route)
        }
        MenuItem(stringResource(R.string.PersonMenuItem3)) {
            personScreenViewModel.getPersonGenres()
            navController.navigate(NavRoute.PersonFavoriteActors.route)
        }
        MenuItem(stringResource(R.string.PersonMenuItem4)) {
            personScreenViewModel.getPersonGenres()
            navController.navigate(NavRoute.PersonSettings.route)
        }
    }
}