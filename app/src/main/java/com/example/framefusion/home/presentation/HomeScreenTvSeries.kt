package com.example.framefusion.home.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.framefusion.home.HomeScreenViewModel

@Composable
fun HomeScreenTvSeries(viewModel: HomeScreenViewModel, provideId: (Int?) -> Unit) {
    Scaffold(
        content = { paddingValues ->
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(paddingValues)
                    .padding(horizontal = 8.dp)
                    .padding(bottom = 80.dp)
            ) {
                Text(text = "TvSeries")
            }
        }
    )
}