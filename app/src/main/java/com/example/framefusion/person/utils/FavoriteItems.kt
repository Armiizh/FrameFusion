package com.example.framefusion.person.utils

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.framefusion.itemDetails.utils.composable.ItemName1
import com.example.framefusion.itemDetails.utils.converters.genreFormatted
import com.example.framefusion.person.data.model.FavoriteItem
import com.example.framefusion.search.utils.SearchScreenGenres
import com.example.framefusion.utils.composable.NoImage
import com.example.framefusion.utils.composable.Poster

@Composable
fun FavoriteItems(
    favoriteItem: FavoriteItem,
    provideId: (Int?) -> Unit
) {
    val detailsGenres = genreFormatted(favoriteItem.genres)
    val ratingKp = favoriteItem.rating.kp

    Row(
        Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .clickable {
                provideId(favoriteItem.id)
            },
        horizontalArrangement = Arrangement.Start
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth(0.4f)
                .height(200.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            if (favoriteItem.poster.url != null && favoriteItem.poster.url != "null") {
                Poster(favoriteItem.poster.url)
            } else {
                NoImage()
            }
        }
        Column(
            modifier = Modifier.fillMaxWidth(1f)
        ) {
            ItemName1(favoriteItem.name.toString(), textAlign = TextAlign.Start)
            Spacer(Modifier.height(12.dp))
            SearchScreenGenres(favoriteItem.genres, detailsGenres)
            Spacer(modifier = Modifier.height(2.dp))
            FavoriteScreenYearAndRating(favoriteItem, ratingKp)
            Spacer(modifier = Modifier.height(2.dp))
            FavoriteScreenDescription(favoriteItem)
        }
    }
}