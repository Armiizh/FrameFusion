package com.example.framefusion.home.utils.homeScreen

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.rememberScrollState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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
            ItemShimmer()
        }
    }
}

