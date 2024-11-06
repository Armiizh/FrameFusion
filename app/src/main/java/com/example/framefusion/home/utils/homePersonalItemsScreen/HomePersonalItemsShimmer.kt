package com.example.framefusion.home.utils.homePersonalItemsScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.valentinilk.shimmer.shimmer

@Preview
@Composable
internal fun HomePersonalItemsShimmer() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .verticalScroll(rememberScrollState())
            .shimmer(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        repeat(5) {
            Row(
                Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Box(
                    modifier = Modifier
                        .width(165.dp)
                        .height(225.5.dp)
                        .clip(RoundedCornerShape(4.dp))
                        .padding(8.dp)
                        .background(color = Color.LightGray)
                )
                Box(
                    modifier = Modifier
                        .width(165.dp)
                        .height(225.5.dp)
                        .clip(RoundedCornerShape(8.dp))
                        .padding(8.dp)
                        .background(color = Color.LightGray)
                )
            }
        }
    }
}