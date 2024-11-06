package com.example.framefusion.home.utils.homeScreen.content

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.framefusion.home.HomeScreenViewModel
import com.example.framefusion.utils.ui.Background
import com.example.framefusion.utils.ui.FrameFusionColumn

@Composable
fun HomeScreenContent(
    paddingValues: PaddingValues,
    homeScreenViewModel: HomeScreenViewModel,
    onItemDetailsScreen: (Int?) -> Unit,
    onHomePersonalItemsScreen: (String) -> Unit
) {
    Background()

    FrameFusionColumn(paddingValues) {

        Top10PersonalMovieContent(
            homeScreenViewModel,
            onItemDetailsScreen,
            onHomePersonalItemsScreen
        )

        Spacer(modifier = Modifier.height(12.dp))

        Top10PersonalTvSeriesContent(
            homeScreenViewModel,
            onItemDetailsScreen,
            onHomePersonalItemsScreen
        )

        Spacer(modifier = Modifier.height(12.dp))
    }
}