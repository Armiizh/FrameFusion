package com.example.framefusion.features.person.utils.personFavoriteMovies.content

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.framefusion.features.person.data.local.model.FavoriteItem
import com.example.framefusion.utils.composable.Item
import com.example.framefusion.utils.navigation.Navigator

@Composable
fun FavoriteMoviesContent(
    favoriteItems: List<FavoriteItem>,
    navigator: Navigator
) {
    LazyColumn(
        modifier = Modifier.fillMaxWidth()
    ) {
        items(favoriteItems) { favoriteItem ->
            Item(
                id = favoriteItem.id,
                posterUrl = favoriteItem.poster?.url,
                name = favoriteItem.name,
                genres = favoriteItem.genres,
                year = favoriteItem.year,
                movieLength = favoriteItem.movieLength,
                totalSeriesLength = favoriteItem.totalSeriesLength,
                seriesLength = favoriteItem.seriesLength,
                type = favoriteItem.type,
                rating = favoriteItem.rating?.kp,
                description = favoriteItem.description,
                shortDescription = favoriteItem.shortDescription
            ) { navigator.navigateToItemDetails(favoriteItem.id) }
        }
    }
}