package com.example.framefusion.features.itemDetails.utils.actorDetailsScreen.content

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.framefusion.features.itemDetails.data.local.models.ActorsMovie
import com.example.framefusion.features.itemDetails.data.local.models.Profession
import com.example.framefusion.features.itemDetails.utils.actorDetailsScreen.composable.ActorPhoto

@Composable
fun ShortActorInfoContent(
    photo: String?,
    name: String?,
    enName: String?,
    age: Int?,
    movies: List<ActorsMovie>?,
    countAwards: Int?,
    sex: String?,
    growth: Int?,
    birthday: String?,
    death: String?,
    profession: List<Profession>?,
) {
    Row(Modifier.fillMaxWidth()) {
        Column(
            Modifier
                .fillMaxWidth(.5f)
                .padding(top = 36.dp, bottom = 12.dp)
                .padding(horizontal = 12.dp)
        ) {
            ActorPhoto(photo)
        }
        Column(
            Modifier
                .weight(1f)
                .padding(top = 36.dp, bottom = 24.dp, end = 12.dp)
        ) {
            ShortActorInfo(
                name,
                enName,
                age,
                movies,
                countAwards,
                sex,
                growth,
                birthday,
                death,
                profession
            )
        }
    }
}