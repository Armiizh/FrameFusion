package com.example.framefusion.search.utils

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import com.example.framefusion.search.data.local.models.SearchItem

@Composable
fun SearchScreenDescription(searchItem: SearchItem) {
    val textDescription =
        if (searchItem.description != null && searchItem.description != "") {
            "${searchItem.description}"
        } else if (searchItem.shortDescription != null && searchItem.shortDescription != "") {
            "${searchItem.shortDescription}"
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