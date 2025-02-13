package com.example.framefusion.features.home.utils.homePersonalItemsScreen.content

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.example.framefusion.utils.composable.IconBack
import com.example.framefusion.utils.composable.NameOfScreen
import com.example.framefusion.utils.navigation.Navigator

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun HomePersonalItemsTopAppBarContent(
    currentType: String,
    navigator: Navigator
) {
    TopAppBar(
        title = {
            NameOfScreen(
                when (currentType) {
                    "movie" -> {
                        "Фильмы"
                    }
                    "tv-series" -> {
                        "Сериалы"
                    }
                    else -> {
                        ""
                    }
                }
            )
        },
        navigationIcon = { IconBack(navigator) },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Color.Transparent
        )
    )
}