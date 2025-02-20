package com.example.framefusion.features.itemDetails.utils.actorDetailsScreen.composable

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.framefusion.features.itemDetails.data.local.models.ActorsMovie

@Composable
fun AgeMoviesAward(
    age: Int?,
    movies: List<ActorsMovie>?,
    countAwards: Int?
) {
    Row(
        Modifier
            .fillMaxWidth()
            .padding(top = 8.dp),
        horizontalArrangement = Arrangement.Center
    ) {
        ShortActorInfoItem(age.toString(), "Возраст")
        VerticalDivider(
            Modifier
                .height(48.dp)
                .padding(horizontal = 4.dp),
            color = Color.LightGray.copy(.5f)
        )
        ShortActorInfoItem(movies?.size.toString(), "Фильмы")
        VerticalDivider(
            Modifier
                .height(48.dp)
                .padding(horizontal = 4.dp),
            color = Color.LightGray.copy(.5f)
        )
        ShortActorInfoItem(countAwards.toString(), "Награды")
    }
}