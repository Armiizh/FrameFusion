package com.example.framefusion.home.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.framefusion.home.HomeScreenViewModel
import com.example.framefusion.home.utils.composable.HomePersonalScreenLoadingContent
import com.example.framefusion.home.utils.composable.PersonalHomeItem
import com.example.framefusion.utils.ui.Background

@Composable
fun HomeMoreItemsScreen(viewModel: HomeScreenViewModel, provideId: (Int?) -> Unit) {

    val isItemLoading by viewModel.personalItemsLoading.collectAsState()

    Scaffold(
        content = { paddingValues ->
            Background()
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(paddingValues)
                    .padding(horizontal = 8.dp)
                    .padding(bottom = 80.dp)
            ) {
                if (isItemLoading) {
                    HomePersonalScreenLoadingContent()
                } else {
                    HomeScreenItemsContent(viewModel, provideId)
                }
            }
        }
    )
}


@OptIn(ExperimentalLayoutApi::class)
@Composable
internal fun HomeScreenItemsContent(viewModel: HomeScreenViewModel, provideId: (Int?) -> Unit) {

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
                    PersonalHomeItem(url) { provideId(item.id) }
                }
            }
        }
    }
}