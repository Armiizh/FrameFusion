package com.example.framefusion.search.utils

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.framefusion.itemDetails.utils.composable.ItemName1
import com.example.framefusion.itemDetails.utils.converters.genreFormatted
import com.example.framefusion.search.data.local.models.SearchItem
import com.example.framefusion.utils.composable.NoImage
import com.example.framefusion.utils.composable.Poster

@Composable
fun SearchItems(
    searchItem: SearchItem,
    onItemDetailsScreen: (Int?) -> Unit
) {
    val detailsGenres = genreFormatted(searchItem.genres!!)
    val ratingKp = searchItem.rating?.kp
    Row(
        Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .clickable {
                onItemDetailsScreen(searchItem.id)
            },
        horizontalArrangement = Arrangement.Start
    ) {
        if (searchItem.poster?.url != null && searchItem.poster.url != "null") {
            Poster(searchItem.poster.url)
        } else {
            NoImage()
        }
//        Column(
//            modifier = Modifier
//                .fillMaxWidth(0.4f),
//            horizontalAlignment = Alignment.CenterHorizontally,
//            verticalArrangement = Arrangement.Center
//        ) {
//            if (searchItem.poster?.url != null && searchItem.poster.url != "null") {
//                Poster(searchItem.poster.url)
//            } else {
//                NoImage()
//            }
//        }
        Column(
            modifier = Modifier.fillMaxWidth(1f)
        ) {
            ItemName1(searchItem.name.toString(), textAlign = TextAlign.Start)
            Spacer(Modifier.height(12.dp))
            SearchScreenGenres(searchItem.genres, detailsGenres)
            Spacer(modifier = Modifier.height(2.dp))
            SearchScreenYearAndRating(searchItem, ratingKp)
            Spacer(modifier = Modifier.height(2.dp))
            SearchScreenDescription(searchItem)
        }
    }
}