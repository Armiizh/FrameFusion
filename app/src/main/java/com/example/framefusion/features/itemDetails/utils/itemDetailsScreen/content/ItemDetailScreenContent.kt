package com.example.framefusion.features.itemDetails.utils.itemDetailsScreen.content

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.example.framefusion.features.itemDetails.DetailsScreenViewModel
import com.example.framefusion.features.itemDetails.utils.itemDetailsScreen.DetailsScreenShimmer
import com.example.framefusion.features.person.PersonScreenViewModel
import com.example.framefusion.utils.navigation.Navigator
import com.example.framefusion.utils.state.Result
import com.example.framefusion.utils.ui.ErrorScreen

//@Composable
//fun ItemDetailsScreenContent(
//    paddingValues: PaddingValues,
//    navigator: Navigator,
//    itemId: Int,
//    personScreenViewModel: PersonScreenViewModel,
//    itemDetailsScreenViewModel: DetailsScreenViewModel
//) {
//
//    val itemDetailsState by itemDetailsScreenViewModel.itemDetailsState.collectAsState()
//
//    LaunchedEffect(Unit) {
//        itemDetailsScreenViewModel.initItemDetails(itemId)
//    }
//
//    when (val state = itemDetailsState) {
//
//        is Result.Loading -> {
//            DetailsScreenShimmer()
//        }
//
//        is Result.Success -> {
//            ItemDetailsScreenSuccessContent(
//                state,
//                navigator,
//                paddingValues,
//                itemDetailsScreenViewModel,
//                personScreenViewModel
//            )
//        }
//
//        is Result.Error -> {
//            ErrorScreen(
//                paddingValues,
//                state.error
//            ) {
//                itemDetailsScreenViewModel.initItemDetails(itemId)
//            }
//        }
//    }
//}
@Composable
fun ItemDetailsScreenContent(
    paddingValues: PaddingValues,
    navigator: Navigator,
    itemId: Int,
    personScreenViewModel: PersonScreenViewModel,
    itemDetailsScreenViewModel: DetailsScreenViewModel
) {

    val itemDetailsState by itemDetailsScreenViewModel.itemDetailsState.collectAsState()

    LaunchedEffect(Unit) {
        itemDetailsScreenViewModel.initItemDetails(itemId)
    }

    when (val state = itemDetailsState) {

        is Result.Loading -> {
            DetailsScreenShimmer()
        }

        is Result.Success -> {
            ItemDetailsScreenSuccessContent(
                state,
                navigator,
                paddingValues,
                itemDetailsScreenViewModel,
                personScreenViewModel
            )
        }

        is Result.Error -> {
            ErrorScreen(
                paddingValues,
                state.error
            ) {
                itemDetailsScreenViewModel.initItemDetails(itemId)
            }
        }
    }
}