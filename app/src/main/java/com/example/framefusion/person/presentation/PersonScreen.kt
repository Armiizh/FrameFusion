package com.example.framefusion.person.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavHostController
import com.example.framefusion.NavRoute
import com.example.framefusion.person.PersonScreenViewModel
import com.example.framefusion.person.utils.MenuItem
import com.example.framefusion.utils.ui.Background

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PersonScreen(
    navController: NavHostController,
    personScreenViewModel: PersonScreenViewModel
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { NameOfScreen("Профиль") }, colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.Transparent
                )
            )
        },
        content = { paddingValues ->
            Background()
            Column(
                modifier = Modifier
                    .padding(paddingValues)
                    .fillMaxSize()
            ) {
                LazyColumn {
                    item {
                        MenuItem("Ваши жанры") {
                            personScreenViewModel.getPersonGenres()
                            navController.navigate(NavRoute.PersonGenres.route)
                        }
                    }
                    item { MenuItem("Избранное") { navController.navigate(NavRoute.PersonFavorite.route) } }
                    item { MenuItem("Настройки") { navController.navigate(NavRoute.PersonSettings.route) } }
                }
            }
        }
    )
}