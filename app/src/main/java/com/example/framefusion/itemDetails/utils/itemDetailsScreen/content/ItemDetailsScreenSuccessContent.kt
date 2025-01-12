package com.example.framefusion.itemDetails.utils.itemDetailsScreen.content

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.framefusion.itemDetails.DetailsScreenViewModel
import com.example.framefusion.itemDetails.data.local.models.ItemDetails
import com.example.framefusion.itemDetails.utils.itemDetailsScreen.ItemDetailsBackdrop
import com.example.framefusion.utils.navigation.Navigator
import com.example.framefusion.utils.state.Result
import com.example.framefusion.utils.ui.Background
import com.example.framefusion.utils.ui.FrameFusionColumn

@Composable
fun ItemDetailsScreenSuccessContent(
    state: Result.Success<ItemDetails>,
    viewModel: DetailsScreenViewModel,
    navigator: Navigator,
    paddingValues: PaddingValues
) {
    Background()
    Column(Modifier.fillMaxWidth()) {
        LazyColumn {
            item { ItemDetailsBackdrop(state.data, viewModel, navigator, paddingValues) }
        }
        FrameFusionColumn(
            paddingValues,
            withoutTop = true
        ) {
            ItemDetailsContent(state.data, navigator)
        }
    }
}