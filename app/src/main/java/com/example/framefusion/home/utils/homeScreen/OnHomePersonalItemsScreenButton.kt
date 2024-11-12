package com.example.framefusion.home.utils.homeScreen

import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.KeyboardArrowRight
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun OnHomePersonalItemsScreenButton(onHomePersonalItems: () -> Unit) {
    IconButton(
        onClick = { onHomePersonalItems() }
    ) {
        Icon(
            modifier = Modifier.size(100.dp),
            imageVector = Icons.AutoMirrored.Rounded.KeyboardArrowRight,
            contentDescription = null
        )
    }
}