package com.example.framefusion.features.search.utils.content

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Clear
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import com.example.framefusion.utils.composable.Title

@Composable
fun SearchBarContent(
    search: String,
    onSearchChange: (String) -> Unit
) {
    Title("Давай найдем что-нибудь")
    TextField(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 12.dp)
            .clip(RoundedCornerShape(12.dp)),
        value = search,
        onValueChange = onSearchChange,
        leadingIcon = {
            Icon(
                imageVector = Icons.Outlined.Search,
                contentDescription = null
            )
        },
        trailingIcon = {
            if (search.isNotEmpty()) {
                Icon(
                    modifier = Modifier.clickable { onSearchChange("") },
                    imageVector = Icons.Outlined.Clear,
                    contentDescription = null
                )
            }
        },
        placeholder = { Text(text = "Поиск") },
        colors = TextFieldDefaults.colors(
            unfocusedContainerColor = Color.Transparent.copy(alpha = 0.2f),
            focusedContainerColor = Color.Transparent.copy(alpha = 0.4f),
            unfocusedIndicatorColor = Color.Transparent,
            focusedIndicatorColor = Color.Transparent
        ),
        keyboardOptions = KeyboardOptions(
            imeAction = ImeAction.Done
        )
    )
}