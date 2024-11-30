package com.example.framefusion.home.utils.homePersonalItemsScreen.content

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import com.example.framefusion.home.utils.homePersonalItemsScreen.PersonalItemsContent
import com.example.framefusion.utils.Navigator
import com.example.framefusion.utils.ui.Background
import com.example.framefusion.utils.ui.FrameFusionColumn


@Composable
fun HomePersonalItemsContent(
    paddingValues: PaddingValues,
    navigator: Navigator,
    onTypeChange: (String) -> Unit
) {
    Background()

    FrameFusionColumn(paddingValues) {
        PersonalItemsContent(onTypeChange, navigator)
    }
}