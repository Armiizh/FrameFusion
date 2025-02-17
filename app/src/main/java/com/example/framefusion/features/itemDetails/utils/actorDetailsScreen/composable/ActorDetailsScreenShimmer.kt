package com.example.framefusion.features.itemDetails.utils.actorDetailsScreen.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.framefusion.utils.ui.Background
import com.valentinilk.shimmer.shimmer

@Preview(showBackground = false)
@Composable
fun ActorDetailsScreenShimmer() {

    Background()
    Column(
        Modifier
            .fillMaxWidth()
            .shimmer()
    ) {
        Box(
            Modifier
                .fillMaxWidth()
                .fillMaxHeight(.4f)
                .clip(RoundedCornerShape(24.dp))
                .background(Color.DarkGray)
                .alpha(.4f)
        ) {
            Column(
                Modifier
                    .fillMaxWidth()
                    .padding(top = 12.dp)
            ) {
                Row(Modifier.fillMaxWidth()) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth(.45f)
                            .fillMaxHeight(.7f)
//                            .height(160.dp)
//                            .width(120.dp)
                            .padding(24.dp)
                            .clip(RoundedCornerShape(24.dp))
                            .background(Color.LightGray),
                    )
                }
            }
        }
    }
}