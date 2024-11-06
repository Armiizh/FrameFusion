package com.example.framefusion.home.utils.homePersonalItemsScreen.content

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavHostController
import com.example.framefusion.utils.composable.IconBack
import com.example.framefusion.utils.composable.NameOfScreen

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun HomePersonalItemsTopAppBarContent(
    currentType: String,
    navController: NavHostController
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
        navigationIcon = { IconBack(navController) },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Color.Transparent
        )
    )
}