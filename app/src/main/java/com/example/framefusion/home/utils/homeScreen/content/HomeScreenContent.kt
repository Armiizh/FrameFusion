package com.example.framefusion.home.utils.homeScreen.content

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.framefusion.home.HomeScreenViewModel
import com.example.framefusion.utils.ui.Background

@Composable
fun HomeScreenContent(
    paddingValues: PaddingValues,
    homeScreenViewModel: HomeScreenViewModel,
    onItemDetailsScreen: (Int?) -> Unit,
    onHomePersonalItemsScreen: (String) -> Unit
) {
    Background()
    Column(
        modifier = Modifier
            .padding(paddingValues)
            .padding(horizontal = 8.dp)
            .padding(bottom = 80.dp)
            .fillMaxWidth()
            .verticalScroll(rememberScrollState()),
    ) {
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