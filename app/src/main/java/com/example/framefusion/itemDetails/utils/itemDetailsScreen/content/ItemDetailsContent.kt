package com.example.framefusion.itemDetails.utils.itemDetailsScreen.content

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.framefusion.itemDetails.DetailsScreenViewModel
import com.example.framefusion.itemDetails.utils.itemDetailsScreen.Cast
import com.example.framefusion.itemDetails.utils.itemDetailsScreen.ItemDetailsDescription
import com.example.framefusion.utils.composable.ItemGenres
import com.example.framefusion.utils.composable.ItemName
import com.example.framefusion.utils.composable.ItemType
import com.example.framefusion.utils.composable.YearLengthRating

@Composable
fun ItemDetailsContent(
    viewModel: DetailsScreenViewModel,
    onFullCastScreen: () -> Unit,
    onActorDetailsScreen: (Int?) -> Unit
) {
    val itemDetails by viewModel.itemDetails.collectAsState()

    ItemName(itemDetails?.name)

    Column(
        Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
            .verticalScroll(rememberScrollState())
    ) {

        ItemType(itemDetails?.type)
        ItemGenres(itemDetails?.genres)
        YearLengthRating(
            itemDetails?.year,
            itemDetails?.movieLength,
            itemDetails?.totalSeriesLength,
            itemDetails?.seriesLength,
            itemDetails?.type,
            itemDetails?.rating?.kp
        )

        Spacer(modifier = Modifier.height(12.dp))

        ItemDetailsDescription(
            itemDetails?.description,
            itemDetails?.shortDescription
        )

        Spacer(modifier = Modifier.height(12.dp))

        Cast(itemDetails?.persons, onFullCastScreen, onActorDetailsScreen)
    }
}