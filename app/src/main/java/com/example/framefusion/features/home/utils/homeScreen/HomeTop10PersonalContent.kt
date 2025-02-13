package com.example.framefusion.features.home.utils.homeScreen

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.framefusion.utils.composable.ErrorView
import com.example.framefusion.utils.composable.Title
import com.example.framefusion.utils.state.Result

@Composable
fun <T> HomeTop10PersonalContent(
    type: String,
    result: Result<List<T>>,
    onHomePersonalItemsScreen: (String) -> Unit,
    itemContent: @Composable (T) -> Unit,
    onRetry: () -> Unit
) {
    Title("$type на основе ваших интересов")
    Spacer(modifier = Modifier.height(8.dp))

    when (result) {
        is Result.Loading -> {
            HomeTop10ItemsShimmer()
        }

        is Result.Success -> {
            if (result.data.isNotEmpty()) {
                Row(
                    modifier = Modifier
                        .horizontalScroll(rememberScrollState()),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    result.data.forEach { item ->
                        itemContent(item)
                    }
                    OnHomePersonalItemsScreenButton {
                        onHomePersonalItemsScreen(type)
                    }
                }
            } else {
                // Состояние, когда список пуст
                Text(
                    text = "Нет персональных рекомендаций для $type",
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center
                )
            }
        }

        is Result.Error -> {
            // View ошибки
            ErrorView(
                message = result.error.getLocalizedMessage(),
                onRetry = { onRetry() }
            )
        }
    }
}