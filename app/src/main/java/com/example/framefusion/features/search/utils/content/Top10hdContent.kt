package com.example.framefusion.features.search.utils.content

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.rememberScrollState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.framefusion.features.search.SearchItemViewModel
import com.example.framefusion.features.search.utils.Top10hdItem
import com.example.framefusion.utils.composable.Title

@Composable
fun Top10hdContent(
    viewModel: SearchItemViewModel,
    onItemDetailsScreen: (Int?) -> Unit
) {
    val top10hd by viewModel.top10hd.collectAsState()
    Title("Топ-10 за месяц:")
    Spacer(modifier = Modifier.height(12.dp))
    Row(
        modifier = Modifier
            .horizontalScroll(rememberScrollState()),
        verticalAlignment = Alignment.CenterVertically
    ) {
        top10hd.forEach { top10hdItem ->
            Top10hdItem(top10hdItem, onItemDetailsScreen)
        }
    }
}