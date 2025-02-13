package com.example.framefusion.features.itemDetails.utils.itemDetailsFullCastScreen

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.framefusion.features.itemDetails.DetailsScreenViewModel
import com.example.framefusion.utils.navigation.Navigator
import com.example.framefusion.utils.ui.Background
import com.example.framefusion.utils.ui.FrameFusionColumn

@Composable
fun ItemDetailsFullCastScreenContent(
    paddingValues: PaddingValues,
    navigator: Navigator,
    detailsScreenViewModel: DetailsScreenViewModel = hiltViewModel()
) {
    val itemDetailsState by detailsScreenViewModel.itemDetailsState.collectAsStateWithLifecycle()
    Background()

    FrameFusionColumn(paddingValues) {

        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
        ) {
//            val data = (itemDetailsState as Result.Success).data
//            if (data != null) {
//                items(data.persons) { person ->
//                    PersonItem(person, navigator)
//                }
//            }
        }
    }
}