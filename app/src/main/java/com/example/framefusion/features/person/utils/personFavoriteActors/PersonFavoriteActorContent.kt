package com.example.framefusion.features.person.utils.personFavoriteActors

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.framefusion.R
import com.example.framefusion.features.person.PersonScreenViewModel
import com.example.framefusion.features.person.utils.personFavoriteMovies.content.PersonFavoriteMoviesEmptyContent
import com.example.framefusion.utils.composable.Title
import com.example.framefusion.utils.navigation.Navigator
import com.example.framefusion.utils.state.Result
import com.example.framefusion.utils.ui.Background
import com.example.framefusion.utils.ui.FrameFusionColumn

@Composable
fun PersonFavoriteActorsContent(
    paddingValues: PaddingValues,
    navigator: Navigator,
    personScreenViewModel: PersonScreenViewModel
) {
    val favoritesItem by personScreenViewModel.favoritesActors.collectAsState()

    Background()

    FrameFusionColumn(paddingValues) {

        when (val state = favoritesItem) {
            is Result.Loading -> {
                Text(text = "Loading")
            }

            is Result.Error -> {
                Text(text = "Loading")
            }

            is Result.Success -> {
                Title(stringResource(R.string.Your_favorite_items))

                Spacer(Modifier.height(12.dp))

                if (state.data.isEmpty()) {
                    PersonFavoriteMoviesEmptyContent(navigator)
                } else {
                    FavoriteActorContent(state.data, navigator)
                }
            }
        }
    }
}