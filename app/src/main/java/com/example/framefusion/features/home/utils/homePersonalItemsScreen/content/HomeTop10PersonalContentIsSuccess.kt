package com.example.framefusion.features.home.utils.homePersonalItemsScreen.content

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import com.example.framefusion.features.home.utils.homeScreen.OnHomePersonalItemsScreenButton
import com.example.framefusion.utils.state.Result

@Composable
fun <T> HomeTop10PersonalContentIsSuccess(
    result: Result.Success<List<T>>,
    itemContent: @Composable (T) -> Unit,
    onHomePersonalItemsScreen: (String) -> Unit,
    type: String
) {
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


