package com.example.framefusion.person.utils.composable

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import com.example.framefusion.person.data.model.FavoriteItem

@Composable
internal fun FavoriteScreenDescription(favoriteItem: FavoriteItem) {
    val textDescription =
        if (favoriteItem.description != null && favoriteItem.description != "") {
            "${favoriteItem.description}"
        } else if (favoriteItem.shortDescription != null && favoriteItem.shortDescription != "") {
            "${favoriteItem.shortDescription}"
        } else {
            "Ребята пока не добавили описание к своему фильму."
        }
    Text(
        textAlign = TextAlign.Justify,
        text = textDescription,
        maxLines = 4,
        overflow = TextOverflow.Ellipsis
    )
}