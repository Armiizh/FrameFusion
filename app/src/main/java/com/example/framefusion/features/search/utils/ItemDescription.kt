package com.example.framefusion.features.search.utils

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import com.example.framefusion.R

@Composable
fun ItemDescription(
    description: String?,
    shortDescription: String?
) {
    val textDescription =
        if (description != null && description != "" && description != "null") {
            "$description"
        } else if (shortDescription != null && shortDescription != "" && shortDescription != "null") {
            "$shortDescription"
        } else {
            stringResource(R.string.Empty_description)
        }

    Text(
        textAlign = TextAlign.Justify,
        text = textDescription,
        maxLines = 4,
        overflow = TextOverflow.Ellipsis
    )
}