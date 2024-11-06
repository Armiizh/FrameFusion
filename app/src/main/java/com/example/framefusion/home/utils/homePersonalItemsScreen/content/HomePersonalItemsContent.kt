package com.example.framefusion.home.utils.homePersonalItemsScreen.content

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.framefusion.home.HomeScreenViewModel
import com.example.framefusion.home.utils.homePersonalItemsScreen.PersonalItemsContent
import com.example.framefusion.utils.ui.Background

@Composable
fun HomePersonalItemsContent(
    paddingValues: PaddingValues,
    viewModel: HomeScreenViewModel,
    onItemDetailsScreen: (Int?) -> Unit,
    onTypeChange: (String) -> Unit
) {
    Background()
    Column(
        modifier = Modifier
            .padding(paddingValues)
            .padding(horizontal = 8.dp)
            .padding(bottom = 80.dp)
            .fillMaxWidth()
    ) {
        PersonalItemsContent(viewModel, onTypeChange, onItemDetailsScreen)
    }
}