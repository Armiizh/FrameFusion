package com.example.framefusion.itemDetails.utils.itemDetailsFullCastScreen

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.framefusion.itemDetails.DetailsScreenViewModel
import com.example.framefusion.itemDetails.utils.PersonItem
import com.example.framefusion.utils.Navigator
import com.example.framefusion.utils.ui.Background
import com.example.framefusion.utils.ui.FrameFusionColumn

@Composable
fun ItemDetailsFullCastScreenContent(
    paddingValues: PaddingValues,
    navigator: Navigator,
    viewModel: DetailsScreenViewModel = hiltViewModel()
) {
    val itemDetails by viewModel.itemDetails.collectAsState()

    Background()

    FrameFusionColumn(paddingValues) {

        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
        ) {
            items(itemDetails?.persons ?: emptyList()) { person ->
                PersonItem(person, navigator)
            }
        }
    }
}