package com.example.framefusion.itemDetails.utils.itemDetailsScreen

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.DividerDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.framefusion.R
import com.example.framefusion.itemDetails.data.local.models.Person
import com.example.framefusion.itemDetails.utils.PersonItem

@Composable
fun Cast(persons: List<Person>?, onFullCastScreen: () -> Unit) {
    Text(text = stringResource(id = R.string.Cast))

    HorizontalDivider(
        thickness = DividerDefaults.Thickness,
        modifier = Modifier
            .height(1.dp)
            .fillMaxWidth()
    )
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp)
    ) {
        items(persons?.take(4) ?: emptyList()) { person ->
            PersonItem(person)
        }
    }
    OnFullCastScreen(onFullCastScreen)
}