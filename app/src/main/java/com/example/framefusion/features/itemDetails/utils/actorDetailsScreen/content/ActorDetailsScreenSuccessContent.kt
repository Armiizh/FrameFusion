package com.example.framefusion.features.itemDetails.utils.actorDetailsScreen.content

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.SpringSpec
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.framefusion.features.itemDetails.data.local.models.ActorDetails
import com.example.framefusion.features.itemDetails.data.local.models.ActorsMovie
import com.example.framefusion.features.itemDetails.utils.actorDetailsScreen.composable.FactInfo
import com.example.framefusion.features.itemDetails.utils.actorDetailsScreen.composable.SubscribeButton
import com.example.framefusion.utils.composable.Poster
import com.example.framefusion.utils.navigation.Navigator
import com.example.framefusion.utils.state.Result
import com.example.framefusion.utils.ui.FrameFusionColumn

@Composable
fun ActorDetailsScreenSuccessContent(
    paddingValues: PaddingValues,
    navigator: Navigator,
    state: Result.Success<ActorDetails>,
    onFavoriteActor: () -> Unit,
    getMoviesInfo: () -> Unit,
) {
    FrameFusionColumn(
        paddingValues,
        modifier = Modifier,
        withoutHorizontal = true
    ) {

        LaunchedEffect(Unit) {
            getMoviesInfo()
        }

        Column(
            Modifier
                .fillMaxWidth()
                .verticalScroll(rememberScrollState())
        ) {
            var isExpanded by remember { mutableStateOf(false) }

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(24.dp))
                    .background(Color.Magenta.copy(alpha = .1f))
                    .animateContentSize(remember { SpringSpec(stiffness = Spring.StiffnessLow) })

            ) {
                Column(Modifier.fillMaxWidth()) {

                    ShortActorInfoContent(
                        state.data.photo,
                        state.data.name,
                        state.data.enName,
                        state.data.age,
                        state.data.movies,
                        state.data.countAwards,
                        state.data.sex,
                        state.data.growth,
                        state.data.birthday,
                        state.data.death,
                        state.data.profession
                    )

                    FactInfo(
                        state.data.facts,
                        isExpanded
                    ) { isExpanded = !isExpanded }

                    SubscribeButton(
                        state.data.isFavorite
                    ) { onFavoriteActor() }
                }
            }

            MoviesInfo(state.data.movies, navigator)
        }
    }
}

@Composable
private fun MoviesInfo(
    movies: List<ActorsMovie>?,
    navigator: Navigator
) {
    Column(Modifier.fillMaxWidth()) {

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp, vertical = 24.dp)
                .clip(RoundedCornerShape(24.dp))
                .background(Color.Transparent)
                .border(
                    border = BorderStroke(4.dp, color = Color.Magenta.copy(.1f)),
                    shape = RoundedCornerShape(24.dp)
                ),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "Фильмы ${movies?.size}",
                modifier = Modifier.padding(vertical = 12.dp)
            )
        }
        Row(
            modifier = Modifier.horizontalScroll(rememberScrollState()),
            verticalAlignment = Alignment.CenterVertically
        ) {
            movies?.take(10)?.forEach { item ->
                Poster(item.poster?.url) {
                    navigator.navigateToItemDetails(item.id)
                }
            }
//            OnHomePersonalItemsScreenButton {
//                onHomePersonalItemsScreen(type)
//            }
        }
    }
}
