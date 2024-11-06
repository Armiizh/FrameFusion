package com.example.framefusion.itemDetails.utils.itemDetailsScreen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextDecoration
import com.example.framefusion.R

@Composable
fun OnFullCastScreen(onFullCastScreen: () -> Unit) {
    Row(
        Modifier.fillMaxWidth()
    ) {
        Text(
            text = stringResource(R.string.Full_cast),
            color = MaterialTheme.colorScheme.onBackground,
            textDecoration = TextDecoration.Underline,
            modifier = Modifier.clickable { onFullCastScreen() }
        )
    }
}