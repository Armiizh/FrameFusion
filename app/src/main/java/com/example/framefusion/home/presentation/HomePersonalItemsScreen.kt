package com.example.framefusion.home.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.framefusion.home.HomeScreenViewModel
import com.example.framefusion.home.utils.composable.HomePersonalItems
import com.example.framefusion.home.utils.composable.HomePersonalItemsShimmer
import com.example.framefusion.home.utils.composable.TopAppBarContent
import com.example.framefusion.utils.ui.Background

@Composable
fun HomePersonalItemsScreen(
    viewModel: HomeScreenViewModel,
    navController: NavHostController,
    provideId: (Int?) -> Unit
) {
    val isItemLoading by viewModel.personalItemsLoading.collectAsState()
    var currentType by remember { mutableStateOf("") }
    Scaffold(
        topBar = {
            TopAppBarContent(currentType, navController)
        },
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
                    HomePersonalItemsShimmer()
                } else {
                    HomePersonalItems(viewModel, provideId) { type -> currentType = type }
                }
            }
        }
    )
}