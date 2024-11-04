package com.example.framefusion.home.utils.composable

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.valentinilk.shimmer.shimmer

@Composable
fun HomeTop10ItemsShimmer() {
    Row(
        modifier = Modifier
            .padding(start = 8.dp)
            .horizontalScroll(rememberScrollState())
            .shimmer(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        repeat(10) {
            ItemShimmer()
        }
    }
}

