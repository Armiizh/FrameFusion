package com.example.framefusion.home.utils.homeScreen

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.framefusion.utils.composable.ItemShimmer
import com.valentinilk.shimmer.shimmer

@Composable
fun HomeTop10ItemsShimmer() {
    Row(
        modifier = Modifier
            .horizontalScroll(rememberScrollState())
            .shimmer(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        repeat(10) {
            Box(
                modifier = Modifier
                    .width(165.dp)
                    .height(225.5.dp)
                    .padding(end = 12.dp, bottom = 12.dp),
                contentAlignment = Alignment.Center
            ) {
                ItemShimmer()
            }
        }
    }
}