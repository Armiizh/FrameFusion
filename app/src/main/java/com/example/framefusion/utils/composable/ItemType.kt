package com.example.framefusion.utils.composable

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.example.framefusion.R

@Composable
fun ItemType(type: String?) {
    if (type != null && type != "null" && type != "") {
        Column(Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
            when (type) {
                "movie" -> {
                    Text(text = stringResource(R.string.movie))
                }

                "tv-series" -> {
                    Text(text = stringResource(R.string.Tv_series))
                }

                else -> Text(type)
            }
        }
    }
}