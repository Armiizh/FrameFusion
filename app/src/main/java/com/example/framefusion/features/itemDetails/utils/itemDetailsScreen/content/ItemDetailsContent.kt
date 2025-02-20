package com.example.framefusion.features.itemDetails.utils.itemDetailsScreen.content

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.framefusion.features.itemDetails.data.local.models.ItemDetails
import com.example.framefusion.features.itemDetails.utils.itemDetailsScreen.Cast
import com.example.framefusion.features.itemDetails.utils.itemDetailsScreen.ItemDetailsDescription
import com.example.framefusion.utils.composable.ItemGenres
import com.example.framefusion.utils.composable.ItemName
import com.example.framefusion.utils.composable.ItemType
import com.example.framefusion.utils.composable.YearLengthRating
import com.example.framefusion.utils.navigation.Navigator

@Composable
fun ItemDetailsContent(
    itemDetails: ItemDetails,
    navigator: Navigator
) {
    ItemName(itemDetails.name)
    Column(
        Modifier
            .fillMaxWidth()
            .verticalScroll(rememberScrollState())
    ) {

        ItemType(itemDetails.type)
        ItemGenres(itemDetails.genres)
        YearLengthRating(
            itemDetails.year,
            itemDetails.movieLength,
            itemDetails.totalSeriesLength,
            itemDetails.seriesLength,
            itemDetails.type,
            itemDetails.rating.kp
        )

        Spacer(Modifier.height(12.dp))

        ItemDetailsDescription(
            itemDetails.description,
            itemDetails.shortDescription
        )

        Spacer(Modifier.height(12.dp))

        Cast(itemDetails.persons, navigator)
    }
}