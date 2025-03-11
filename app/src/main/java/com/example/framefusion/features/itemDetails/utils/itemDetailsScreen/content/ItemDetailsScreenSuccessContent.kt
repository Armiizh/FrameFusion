package com.example.framefusion.features.itemDetails.utils.itemDetailsScreen.content

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import com.example.framefusion.features.itemDetails.DetailsScreenViewModel
import com.example.framefusion.features.itemDetails.data.local.models.ItemDetails
import com.example.framefusion.features.itemDetails.utils.itemDetailsScreen.ItemDetailsBackdrop
import com.example.framefusion.features.person.PersonScreenViewModel
import com.example.framefusion.utils.navigation.Navigator
import com.example.framefusion.utils.ui.Background
import com.example.framefusion.utils.ui.FrameFusionColumn

@Composable
fun ItemDetailsScreenSuccessContent(
    paddingValues: PaddingValues,
    itemDetails: ItemDetails,
    navigator: Navigator,
    itemDetailsScreenViewModel: DetailsScreenViewModel,
    personScreenViewModel: PersonScreenViewModel
) {
    Background()
    FrameFusionColumn(
        paddingValues,
        withoutTop = true,
        withoutHorizontal = true
    ) {
        ItemDetailsBackdrop(
            itemDetails,
            navigator,
            paddingValues,
        )
        ItemDetailsContent(itemDetails, navigator) {
            val isFavorite = !(itemDetails.isFavorite)
            personScreenViewModel.changeFavoriteStatus(itemDetails, isFavorite)
            itemDetails.id?.let {
                itemDetailsScreenViewModel.updateItem(
                    it,
                    isFavorite
                )
            }
        }
    }
}