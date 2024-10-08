package com.example.framefusion.person.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavHostController
import com.example.framefusion.NavRoute
import com.example.framefusion.person.PersonScreenViewModel
import com.example.framefusion.person.utils.MenuItem
import kotlinx.coroutines.launch

@Composable
fun PersonScreen(
    navController: NavHostController,
    personScreenViewModel: PersonScreenViewModel
) {
    Scaffold(
        content = { paddingValues ->
            Column(
                modifier = Modifier
                    .padding(paddingValues)
                    .fillMaxSize()
            ) {
                LazyColumn {
                    item {
                        MenuItem("Ваши жанры") {
                            personScreenViewModel.viewModelScope.launch {
                                personScreenViewModel.getPersonGenres()
                            }
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