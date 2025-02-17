package com.example.framefusion.features.itemDetails.utils.actorDetailsScreen.content

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.framefusion.features.itemDetails.data.local.models.ActorDetails
import com.example.framefusion.features.itemDetails.data.local.models.Facts
import com.example.framefusion.features.itemDetails.utils.actorDetailsScreen.composable.ActorPhoto
import com.example.framefusion.utils.state.Result
import com.example.framefusion.utils.ui.FrameFusionColumn

@Composable
fun ActorDetailsScreenSuccessContent(
    paddingValues: PaddingValues,
    state: Result.Success<ActorDetails>
) {
    FrameFusionColumn(
        paddingValues,
        modifier = Modifier,
        withoutHorizontal = true
    ) {

        var isExpanded by remember { mutableStateOf(false) }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(24.dp))
                .background(Color.Magenta.copy(alpha = .1f))
                .animateContentSize()
                .verticalScroll(rememberScrollState())
        ) {
            Column(Modifier.fillMaxWidth()) {

                Row(Modifier.fillMaxWidth()) {
                    Column(
                        Modifier
                            .fillMaxWidth(.5f)
                            .padding(top = 36.dp, bottom = 12.dp)
                            .padding(horizontal = 12.dp)
                    ) {
                        ActorPhoto(state.data.photo)
                    }
                    Column(
                        Modifier
                            .weight(1f)
                            .padding(top = 36.dp, bottom = 24.dp, end = 12.dp)
                    ) {
                        ShortActorInfo(
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
                    }
                }

                FactInfo(state.data.facts, isExpanded) { isExpanded = !isExpanded }
                Spacer(modifier = Modifier.height(12.dp))
            }
        }
    }
}

@Composable
fun FactInfo(
    facts: List<Facts>?,
    isExpanded: Boolean,
    onExpandChange: () -> Unit
) {
    val listOfFacts = facts?.map { it.value }
    val maxLines = 5


    if (!listOfFacts.isNullOrEmpty()) {
        Column(
            modifier = Modifier
                .padding(horizontal = 12.dp)
                .fillMaxWidth()
                .clickable { onExpandChange() }
                .animateContentSize()
        ) {
            Text(
                text = "А Вы знали, что...",
                modifier = Modifier.padding(vertical = 8.dp),
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )

            Text(
                text = listOfFacts.joinToString("\n\n"),
                color = Color.White.copy(alpha = 0.5f),
                fontSize = 14.sp,
                lineHeight = 20.sp,
                maxLines = if (isExpanded) Int.MAX_VALUE else maxLines,
                overflow = TextOverflow.Ellipsis,
                textAlign = TextAlign.Justify,
                modifier = Modifier
                    .fillMaxWidth()
                    .animateContentSize()
            )
        }
    }
}