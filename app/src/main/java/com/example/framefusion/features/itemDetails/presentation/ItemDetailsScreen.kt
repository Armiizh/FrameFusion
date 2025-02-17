package com.example.framefusion.features.itemDetails.presentation

import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import com.example.framefusion.features.itemDetails.DetailsScreenViewModel
import com.example.framefusion.features.itemDetails.utils.itemDetailsScreen.content.ItemDetailsScreenContent
import com.example.framefusion.features.person.PersonScreenViewModel
import com.example.framefusion.utils.navigation.Navigator

@Composable
fun ItemDetailsScreen(
    navigator: Navigator,
    itemId: Int,
    personScreenViewModel: PersonScreenViewModel,
    itemDetailsScreenViewModel: DetailsScreenViewModel
) {
    Scaffold(
        content = { paddingValues ->
            ItemDetailsScreenContent(
                paddingValues,
                navigator,
                itemId,
                personScreenViewModel,
                itemDetailsScreenViewModel
            )
        }
    )
}