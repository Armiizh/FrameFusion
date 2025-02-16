package com.example.framefusion.features.person.utils.personFavoriteMovies.content

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextDecoration
import com.example.framefusion.R
import com.example.framefusion.utils.navigation.Navigator

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun PersonFavoriteMoviesEmptyContent(
    navigator: Navigator
) {
    Column(
        Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = stringResource(R.string.Here_is_empty))
        FlowRow(
            Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            Text(text = stringResource(R.string.Add_item_can))
            Text(
                modifier = Modifier.clickable { navigator.navigateToHome() },
                textDecoration = TextDecoration.Underline,
                color = Color.Blue,
                text = stringResource(R.string.Main_screen)
            )
            Text(text = " экране или экране ")
            Text(
                modifier = Modifier.clickable { navigator.navigateToSearch() },
                textDecoration = TextDecoration.Underline,
                color = Color.Blue,
                text = stringResource(R.string.Search_screen)
            )
        }
    }
}