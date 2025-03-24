package com.example.framefusion.features.person.utils.personScreen

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.example.framefusion.R
import com.example.framefusion.features.person.PersonScreenViewModel
import com.example.framefusion.utils.navigation.Navigator
import com.example.framefusion.utils.ui.Background
import com.example.framefusion.utils.ui.FrameFusionColumn

@Composable
fun PersonScreenContent(
    paddingValues: PaddingValues,
    navigator: Navigator,
    personScreenViewModel: PersonScreenViewModel
) {
    Background()
    FrameFusionColumn(paddingValues, withoutScroll = true) {

        MenuItem(stringResource(R.string.PersonMenuItem1)) {
            personScreenViewModel.getPersonGenres()
            navigator.navigateToPersonFavoriteGenres()
        }
        MenuItem(stringResource(R.string.PersonMenuItem2)) {
            navigator.navigateToPersonFavoriteMovies()
        }
        MenuItem(stringResource(R.string.PersonMenuItem3)) {
            navigator.navigateToPersonFavoriteActors()
        }
        MenuItem(stringResource(R.string.PersonMenuItem4)) {
            navigator.navigateToPersonSettings()
        }
    }
}