package com.example.framefusion.features.itemDetails.utils.itemDetailsScreen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.DividerDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.framefusion.R

@Composable
fun ItemDetailsDescription(
    description: String?,
    shortDescription: String?
) {
    Text(text = stringResource(id = R.string.Description), fontSize = 18.sp)

    HorizontalDivider(
        thickness = DividerDefaults.Thickness,
        modifier = Modifier
            .height(1.dp)
            .fillMaxWidth()
    )

    Spacer(modifier = Modifier.height(12.dp))

    val textDescription =
        if (description != null && description != "" && description != "null") {
            "$description"
        } else if (shortDescription != null && shortDescription != "" && shortDescription != "null") {
            "$shortDescription"
        } else {
            stringResource(R.string.Empty_description)
        }

    var isExpanded by remember { mutableStateOf(false) }
    var actualLineCount by remember { mutableIntStateOf(0) }
    var maxLines by remember { mutableIntStateOf(6) }

    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.Start
    ) {
        Text(
            textAlign = TextAlign.Justify,
            softWrap = true,
            modifier = Modifier.fillMaxWidth(),
            text = textDescription,
            maxLines = if (isExpanded) Int.MAX_VALUE else maxLines,
            onTextLayout = { textLayoutResult ->
                actualLineCount = textLayoutResult.lineCount
                if (actualLineCount > 5 && !isExpanded) {
                    maxLines = 6
                }
            }
        )
        if (actualLineCount > 5) {
            Row(
                Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                TextButton(
                    onClick = {
                        isExpanded = !isExpanded
                    },
                ) {
                    Text(
                        text = if (isExpanded) {
                            stringResource(R.string.Hide)
                        } else {
                            stringResource(R.string.Expand)
                        },
                        modifier = Modifier.padding(vertical = 8.dp),
                        color = MaterialTheme.colorScheme.onBackground
                    )
                }
                Icon(
                    imageVector = if (isExpanded) {
                        Icons.Default.KeyboardArrowUp
                    } else {
                        Icons.Default.KeyboardArrowDown
                    }, contentDescription = null
                )
            }
        }
    }
}