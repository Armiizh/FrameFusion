package com.example.framefusion.person.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavHostController
import com.example.framefusion.NavRoute
import com.example.framefusion.R
import com.example.framefusion.person.PersonScreenViewModel
import com.example.framefusion.person.utils.composable.MenuItem

@Composable
fun PersonScreen(
    navController: NavHostController,
    personScreenViewModel: PersonScreenViewModel
) {
    Scaffold(
        content = { paddingValues ->
            Image(
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop,
                painter = painterResource(id = R.drawable.background1),
                contentDescription = null
            )
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