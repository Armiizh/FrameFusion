package com.example.framefusion.features.itemDetails.presentation

import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import com.example.framefusion.features.itemDetails.DetailsScreenViewModel
import com.example.framefusion.features.itemDetails.utils.actorDetailsScreen.content.ActorDetailsScreenContent
import com.example.framefusion.utils.navigation.Navigator
import com.example.framefusion.utils.ui.Background

@Composable
fun ActorsDetailsScreen(
    navigator: Navigator, id: Int, detailsScreenViewModel: DetailsScreenViewModel
) {
    Scaffold { paddingValues ->
        Background()
        ActorDetailsScreenContent(paddingValues, id, navigator, detailsScreenViewModel)
    }
}