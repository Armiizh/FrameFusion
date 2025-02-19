package com.example.framefusion.features.itemDetails.presentation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.example.framefusion.features.itemDetails.DetailsScreenViewModel
import com.example.framefusion.features.itemDetails.utils.actorDetailsScreen.content.ActorDetailsScreenSuccessContent
import com.example.framefusion.features.itemDetails.utils.itemDetailsScreen.DetailsScreenShimmer
import com.example.framefusion.utils.navigation.Navigator
import com.example.framefusion.utils.state.AppError
import com.example.framefusion.utils.state.Result
import com.example.framefusion.utils.ui.Background

@Composable
fun ActorsDetailsScreen(
    navigator: Navigator,
    id: Int,
    detailsScreenViewModel: DetailsScreenViewModel
) {
    Scaffold { paddingValues ->
        Background()
        ActorDetailsScreenContent(paddingValues, id, detailsScreenViewModel)
    }
}

@Composable
fun ActorDetailsScreenContent(
    paddingValues: PaddingValues,
    id: Int,
    detailsScreenViewModel: DetailsScreenViewModel
) {
    val actorDetailsState by detailsScreenViewModel.actorDetailsState.collectAsState()

    LaunchedEffect(Unit) { detailsScreenViewModel.initActorDetails(id) }

    when (val state = actorDetailsState) {

        is Result.Loading -> {
            DetailsScreenShimmer()
        }

        is Result.Success -> {
            ActorDetailsScreenSuccessContent(paddingValues, state) {
                state.data.id?.let {
                    detailsScreenViewModel.updateActor(
                        it, !state.data.isFavorite
                    )
                }
            }
        }

        is Result.Error -> {
            ErrorScreenActor(state.error)
        }
    }
}


@Composable
fun ErrorScreenActor(
    error: AppError
) {
    Text(text = "Error - ${error.getLocalizedMessage()}")
}