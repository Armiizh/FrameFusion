package com.example.framefusion.utils.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.unit.dp

fun Modifier.defaultColumnModifier() = this
    .padding(horizontal = 16.dp)
    .padding(bottom = 80.dp)
    .fillMaxWidth()

//@Composable
//fun FrameFusionColumn(
//    paddingValues: PaddingValues,
//    content: @Composable () -> Unit
//) {
//    Column(
//        modifier = Modifier
//            .padding(paddingValues)
//            .defaultColumnModifier()
//    ) {
//        content()
//    }
//}

@Composable
fun FrameFusionColumn(
    paddingValues: PaddingValues,
    content: @Composable () -> Unit
) {
    val layoutDirection = LocalLayoutDirection.current

    Column(
        modifier = Modifier
            .padding(paddingValues)
            .defaultColumnModifier()
    ) {
        content()
    }
}

@Composable
fun FrameFusionColumn(
    paddingValues: PaddingValues,
    withoutTop: Boolean = false,
    content: @Composable () -> Unit
) {
    val layoutDirection = LocalLayoutDirection.current

    Column(
        modifier = Modifier
            .padding(
                start = paddingValues.calculateStartPadding(layoutDirection),
                end = paddingValues.calculateEndPadding(layoutDirection),
                bottom = paddingValues.calculateBottomPadding(),
                top = if (withoutTop) 0.dp else paddingValues.calculateTopPadding()
            )
            .defaultColumnModifier()
    ) {
        content()
    }
}