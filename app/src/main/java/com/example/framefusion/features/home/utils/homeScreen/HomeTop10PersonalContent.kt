package com.example.framefusion.features.home.utils.homeScreen

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.framefusion.features.home.utils.homePersonalItemsScreen.content.HomeTop10PersonalContentIsSuccess
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
    val stableType by rememberUpdatedState(type)
    val stableOnRetry by rememberUpdatedState(onRetry)

    Title("$stableType на основе ваших интересов")
    Spacer(modifier = Modifier.height(8.dp))

    when (result) {
        is Result.Loading -> {
            HomeTop10ItemsShimmer()
        }

        is Result.Error -> {
            ErrorView(result.error.getLocalizedMessage()) { stableOnRetry() }
        }

        is Result.Success -> {
            HomeTop10PersonalContentIsSuccess(result, itemContent, onHomePersonalItemsScreen, type)
        }
    }
}