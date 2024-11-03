package com.example.framefusion.home.utils.composable

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.framefusion.home.HomeScreenViewModel

@OptIn(ExperimentalLayoutApi::class)
@Composable
internal fun HomePersonalItems(
    viewModel: HomeScreenViewModel,
    provideId: (Int?) -> Unit,
    onTypeChange: (String) -> Unit
) {
    val items by viewModel.personalItems.collectAsState()
    Column(
        Modifier
            .fillMaxWidth()
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        FlowRow(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
            items.forEach { item ->
                if (item.poster.url != null && item.poster.url != "null" && item.poster.url != "") {
                    val url = item.poster.url
                    val currentType = item.type ?: ""
                    onTypeChange(currentType)
                    PersonalHomeItem(url) { provideId(item.id) }
                }
            }
        }
    }
}