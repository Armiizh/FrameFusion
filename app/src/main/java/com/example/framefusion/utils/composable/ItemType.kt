package com.example.framefusion.utils.composable

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.framefusion.R

@Composable
fun ItemType(type: String?) {
    if (type != null && type != "null" && type != "") {
        val modifier = Modifier.padding(top = 12.dp)
        val color = Color.White.copy(.5f)
        when (type) {
            "movie" -> {
                Text(
                    modifier = modifier,
                    text = stringResource(R.string.movie),
                    color = color
                )
            }

            "tv-series" -> {
                Text(
                    modifier = modifier,
                    text = stringResource(R.string.Tv_series),
                    color = color
                )
            }

            else -> Text(
                modifier = modifier,
                text = type,
                color = color
            )
        }
    }
}