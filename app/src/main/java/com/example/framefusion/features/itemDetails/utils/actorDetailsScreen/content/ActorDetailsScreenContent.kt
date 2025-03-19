package com.example.framefusion.features.itemDetails.utils.actorDetailsScreen.content

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.example.framefusion.features.itemDetails.DetailsScreenViewModel
import com.example.framefusion.features.itemDetails.utils.itemDetailsScreen.DetailsScreenShimmer
import com.example.framefusion.utils.navigation.Navigator
import com.example.framefusion.utils.state.Result

@Composable
fun ActorDetailsScreenContent(
    paddingValues: PaddingValues,
    id: Int,
    navigator: Navigator,
    detailsScreenViewModel: DetailsScreenViewModel
) {
    val actorDetailsState by detailsScreenViewModel.actorDetailsState.collectAsState()

    LaunchedEffect(Unit) { detailsScreenViewModel.initActorDetails(id) }

    when (val state = actorDetailsState) {

        is Result.Loading -> {
            DetailsScreenShimmer()
        }

        is Result.Success -> {
            ActorDetailsScreenSuccessContent(paddingValues,
                navigator,
                state,
                onFavoriteActor = {
                    state.data.id?.let {
                        detailsScreenViewModel.updateActor(
                            it, !state.data.isFavorite
                        )
                    }
                },
                getMoviesInfo = {
                    state.data.movies?.let {
                        detailsScreenViewModel.initActorMovies(it)
                    }
                })
        }

        is Result.Error -> {
            ErrorScreenActor(state.error)
        }
    }
}