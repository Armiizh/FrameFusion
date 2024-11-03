package com.example.framefusion.home.utils.composable

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavHostController
import com.example.framefusion.itemDetails.utils.composable.IconBack
import com.example.framefusion.person.presentation.NameOfScreen

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun TopAppBarContent(
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