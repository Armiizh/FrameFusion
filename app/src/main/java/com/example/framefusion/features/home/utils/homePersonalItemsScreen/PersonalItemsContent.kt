package com.example.framefusion.features.home.utils.homePersonalItemsScreen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewModelScope
import com.example.framefusion.features.home.HomeScreenViewModel
import com.example.framefusion.utils.composable.ErrorView
import com.example.framefusion.utils.composable.Poster
import com.example.framefusion.utils.navigation.Navigator
import com.example.framefusion.utils.state.Result
import kotlinx.coroutines.launch

@Composable
fun PersonalItemsContent(
    navigator: Navigator,
    type: String?,
    viewModel: HomeScreenViewModel,
    onTypeChange: (String) -> Unit
) {
    LaunchedEffect(Unit) {
        viewModel.viewModelScope.launch {
            viewModel.initHomePersonalItems(type)
        }
    }

    val personalItemsResult by viewModel.personalItems.collectAsState()

    when (val result = personalItemsResult) {
        is Result.Loading -> {
            HomePersonalItemsShimmer()
        }

        is Result.Success -> {
            val items = result.data
            var previousType by remember { mutableStateOf("") }

            if (items.isEmpty()) {
                // Состояние пустого списка
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "Нет персональных элементов",
                        style = MaterialTheme.typography.bodyLarge
                    )
                }
            } else {
                LazyVerticalGrid(
                    columns = GridCells.Fixed(2),
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    items(items) { item ->
                        if (item.poster.url.isValidPoster()) {
                            val currentType = item.type ?: ""
                            if (currentType != previousType) {
                                onTypeChange(currentType)
                                previousType = currentType
                            }
                            Box(
                                modifier = Modifier.wrapContentSize(),
                                contentAlignment = Alignment.Center
                            ) {
                                Poster(item.poster.url) {
                                    navigator.navigateToItemDetails(item.id)
                                }
                            }
                        }
                    }
                }
            }
        }

        is Result.Error -> {
            // Обработка ошибки
            ErrorView(
                message = result.error.getLocalizedMessage(),
                onRetry = {
                    viewModel.viewModelScope.launch {
                        viewModel.initHomePersonalItems(type)
                    }
                }
            )
        }
    }
}

// Расширение для проверки корректности постера
fun String?.isValidPoster(): Boolean {
    return this != null && this != "null" && this.isNotBlank()
}