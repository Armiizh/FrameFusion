package com.example.framefusion.itemDetails.utils.composable

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
fun Description(text: String) {

    var isExpanded by remember { mutableStateOf(false) }
    var actualLineCount by remember { mutableIntStateOf(0) }
    var maxLines by remember { mutableIntStateOf(6) }

    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            textAlign = TextAlign.Justify,
            modifier = Modifier.fillMaxWidth(),
            text = text,
            maxLines = if (isExpanded) Int.MAX_VALUE else maxLines,
            onTextLayout = { textLayoutResult ->
                actualLineCount = textLayoutResult.lineCount
                if (actualLineCount > 5 && !isExpanded) {
                    maxLines = 6
                }
            }
        )
        if (actualLineCount > 5) {
            TextButton(
                onClick = {
                    isExpanded = !isExpanded
                },
            ) {
                Text(
                    text = if (isExpanded) {
                        "Свернуть"
                    } else {
                        "Развернуть"
                    },
                    modifier = Modifier.padding(8.dp),
                    color = MaterialTheme.colorScheme.onBackground
                )
            }
        }
    }
}