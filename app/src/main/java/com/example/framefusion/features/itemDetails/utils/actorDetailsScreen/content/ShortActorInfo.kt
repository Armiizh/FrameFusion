package com.example.framefusion.features.itemDetails.utils.actorDetailsScreen.content

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.framefusion.features.itemDetails.data.local.models.ActorsMovie
import com.example.framefusion.features.itemDetails.data.local.models.Profession
import com.example.framefusion.features.itemDetails.utils.actorDetailsScreen.composable.ActorName
import com.example.framefusion.features.itemDetails.utils.actorDetailsScreen.composable.AgeMoviesAward
import com.example.framefusion.features.itemDetails.utils.actorDetailsScreen.composable.SexGrowthBirthdayDeath

@Composable
fun ShortActorInfo(
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
    Column(Modifier.fillMaxWidth()) {
        ActorName(name, enName)
        AgeMoviesAward(age, movies, countAwards)
        SexGrowthBirthdayDeath(sex, growth, birthday, death, profession)
    }
}