package com.example.framefusion.utils.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

fun Modifier.defaultColumnModifier() = this
    .padding(horizontal = 16.dp)
    .padding(bottom = 80.dp)
    .fillMaxWidth()

@Composable
fun FrameFusionColumn(
    paddingValues: PaddingValues,
    content: @Composable () -> Unit
) {
    Column(
        modifier = Modifier
            .padding(paddingValues)
            .defaultColumnModifier()
    ) {
        content()
    }
}