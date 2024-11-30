package com.example.framefusion.home.utils.homeScreen

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.rememberScrollState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.framefusion.utils.composable.Title

@Composable
fun <T> HomeTop10PersonalContent(
    type: String,
    isLoading: Boolean,
    items: List<T> = emptyList(),
    onHomePersonalItemsScreen: (String) -> Unit,
    itemContent: @Composable (T) -> Unit
) {
    Title("$type на основе ваших интересов")
    Spacer(modifier = Modifier.height(8.dp))
    if (isLoading) {
        HomeTop10ItemsShimmer()
    } else {
        Row(
            modifier = Modifier
                .horizontalScroll(rememberScrollState()),
            verticalAlignment = Alignment.CenterVertically
        ) {
            items.forEach { item ->
                itemContent(item)
            }
            OnHomePersonalItemsScreenButton { onHomePersonalItemsScreen(type) }
        }
    }
}