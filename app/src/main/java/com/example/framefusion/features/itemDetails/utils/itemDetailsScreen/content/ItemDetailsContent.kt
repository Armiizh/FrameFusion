package com.example.framefusion.features.itemDetails.utils.itemDetailsScreen.content

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.framefusion.features.itemDetails.data.local.models.ItemDetails
import com.example.framefusion.features.itemDetails.utils.actorDetailsScreen.FavoriteToggle
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
    navigator: Navigator,
    onClick: () -> Unit
) {
    Column(
        Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp)
    ) {
        Row(
            Modifier
                .fillMaxWidth()
                .padding(vertical = 12.dp),
            Arrangement.SpaceBetween,
            Alignment.CenterVertically
        ) {
            Column(Modifier.weight(0.8f)) {
                ItemName(itemDetails.name)
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
            }
            Column(
                Modifier.weight(0.2f),
                Arrangement.Center,
                Alignment.CenterHorizontally
            ) {
                FavoriteToggle(itemDetails.isFavorite) { onClick() }
            }
        }
        ItemDetailsDescription(
            itemDetails.description,
            itemDetails.shortDescription
        )
        Cast(itemDetails.persons, navigator)
    }
}